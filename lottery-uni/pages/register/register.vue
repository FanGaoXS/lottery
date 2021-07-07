<template>
	<view class="page-container">
		<u-top-tips ref="uTips"></u-top-tips>
		<view class="info-container">
			<!-- 用户头像 -->
			<view class="avatar-container">
				<u-avatar mode="square" :src="userInfo.avatarUrl"></u-avatar>
			</view>
			<!-- 用户微信昵称 -->
			<view class="name-container">
				<text>{{userInfo.nickName}}</text>
			</view>
			<!-- 登记状态 -->
			<view class="status-container">
				<u-icon
					v-show="isRegister"
					name="checkmark-circle-fill"
					color="green"
					size="80"
					label="已登记"
					label-size="40"
					label-color="green"></u-icon>
				<u-icon
					v-show="!isRegister"
					name="close-circle-fill"
					color="red"
					size="80"
					label="未登记"
					label-size="40"
					label-color="red"></u-icon>
			</view>
		</view>
		<view v-show="!isRegister" class="button-container">
			<u-button 
				size="medium"
				:disabled="isRegister"
				@click="handleGetUserProfile"
				type="primary">
				点击登记
			</u-button>
		</view>
	</view>
</template>

<script>
	import {
		registerUser,
		isRegister
	} from '@/api/user.js'
	import {
		setInfo,
		getInfo
	} from '@/utils/info.js'
	export default {
		onLoad() {
			const stroageInfo = getInfo()
			if(stroageInfo!==''){
				// 如果内存中的userInfo不为空（说明曾经可能登记过）
				this.userInfo.nickName = stroageInfo.nickName
				this.userInfo.avatarUrl = stroageInfo.avatarUrl
				isRegister(this.userInfo).then(res=>{ //保险起见仍然需要从后端数据库中查询是否登记
					this.isRegister = res.data
				})
			}
		},
		data() {
			return {
				isRegister: false,
				userInfo:{
					avatarUrl: '',
					nickName: ''
				}
			}
		},
		methods: {
			/*
				关于@getphonenumber的使用：https://www.uviewui.com/components/button.html#events
				关于getProfile的使用：https://developers.weixin.qq.com/community/develop/doc/000cacfa20ce88df04cb468bc52801?highLine=getUserProfile%253Afail
			*/
			handleGetUserProfile(){
				const that = this
				uni.getUserProfile({
					desc: '测试',
					success({userInfo}) {
						//获取用户个人信息成功
						console.log('获取用户个人信息成功->',userInfo);
						that.userInfo.nickName = userInfo.nickName
						that.userInfo.avatarUrl = userInfo.avatarUrl
						setInfo(that.userInfo) //将用户个人信息放置内存
						isRegister(that.userInfo).then(res=>{ //保险起见仍然需要从后端数据库中查询是否登记
							that.isRegister = res.data
							if(!res.data){ //false表示未登记
								that.handleRegisterUser() //往后端登记用户
							}
						})
					},
					fail(e) {
						//获取用户个人信息失败
						console.log('获取用户个人信息失败->',e);
					}
				})
			},
			handleRegisterUser(){
				registerUser(this.userInfo).then(res=>{ //后端响应成功
					console.log(res);
					this.$refs.uTips.show({
						title: res.data?'登记成功':'登记失败',
						type: res.data?'success':'error',
						duration: '2000'
					})
					this.isRegister = res.data
				}).catch(error=>{ //后端响应失败
					console.log('登记错误->',error);
					this.$refs.uTips.show({
						title: '登记失败->'+error,
						type: 'error',
						duration: '2000'
					})
				})
			},
		}
	}
</script>

<style>
	.page-container {
		margin-left: 20rpx;
		margin-right: 20rpx;
	}
	.info-container {
		margin-top: 400rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.name-container{
		margin-top: 20rpx;
	}
	.status-container {
		margin-top: 20rpx;
	}
	.button-container {
		margin-top: 50rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
</style>
