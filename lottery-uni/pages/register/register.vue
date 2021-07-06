<template>
	<view>
		<!-- 用户头像 -->
		<view>
			<u-avatar mode="square" :src="userInfo.avatarUrl"></u-avatar>
		</view>
		<!-- 用户微信昵称 -->
		<view>
			<text>{{userInfo.nickName}}</text>
		</view>
		<view>
			<text>{{isRegister?'已登记':'未登记'}}</text>
		</view>
		<view>
			<u-button 
				size="medium"
				@click="handleGetUserProfile"
				type="primary">
				点击登记
			</u-button>
		</view>
	</view>
</template>

<script>
	export default {
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
						console.log(userInfo);
						that.userInfo.nickName = userInfo.nickName
						that.userInfo.avatarUrl = userInfo.avatarUrl
					},
					fail(e) {
						//获取用户个人信息失败
						console.log(e);
					}
				})
			},
		}
	}
</script>

<style>

</style>
