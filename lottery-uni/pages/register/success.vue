<template>
	<view class="page-container">
		<u-toast ref="uToast" />
		<register-page 
			:title="place.name"
			:show-foot="true"
			:show-head="true">
			<view slot="body" class="body-container">

				<!-- 微信头像组 -->
				<view class="avatar-container">
					<u-avatar size="150" mode="square" :src="userInfo.avatar"></u-avatar>
				</view>
				
				<view class="status-container">
					<u-icon
						v-show="isRegister"
						name="checkmark-circle-fill"
						color="green"
						size="80"
						label="已登记"
						label-size="40"
						label-color="green">
					</u-icon>
					<text v-show="!isRegister">您还未登记</text>
				</view>
			</view>
			<view slot="foot" class="foot-container">
				<view class="time-container">
					{{userInfo.time | timeFilter }}
				</view>
			</view>
		</register-page>
		
	</view>
</template>

<script>
	
	import {
		getRegisterById,
		getUserListByPlaceId
	} from '@/api/user.js'

	
	import registerPage from './registerPage'
	import avatarList from './avatarList.vue'
	
	
	export default {
		filters: {
			timeFilter: function(value) {
				if(value===undefined) return ''
				return new Date(value).toLocaleString();
			}
		},
		components: {
			registerPage,
			avatarList
		},
		onLoad({id}) { //页面一加载就获取登记的编号
			this.id = id
			this.fetchRegister()
		},
		onPullDownRefresh() {
			this.fetchRegister()
		},
		methods: {
			fetchRegister() {
				getRegisterById(this.id).then(res=>{
					// console.log(res);
					if(res.data!==null){
						this.isRegister = true
						this.userInfo.name = res.data.name
						this.userInfo.avatar = res.data.avatar
						this.userInfo.time = res.data.time
						this.place.id = res.data.place.id
						this.place.name = res.data.place.name
						this.place.address = res.data.place.address
					} else { //如果登记失效
						const that = this
						this.$refs.uToast.show({
							title: '您已清除登记',
							type: 'error',
							duration: '1000',
							callback(){
								that.routerToIndex() //回到首页
							}
						})
					}
					this.fetchOtherUserList()
					uni.stopPullDownRefresh()
				})
			},
			async fetchOtherUserList(){
				const { data } = await getUserListByPlaceId(this.place.id)
				this.otherUserList = data.items
			},
			routerToIndex(){
				this.$u.route({
					type: 'reLaunch',
					url: 'pages/index/index'
				})
			}
		},
		data() {
			return {
				isRegister: false,
				userInfo: {
					id: undefined,
					name: '',
					avatar: '',
					time: undefined
				},
				place: {
					id: undefined,
					name: '',
					address: ''
				},
				otherUserList:[]
			}
		},
	}
</script>

<style>
	.body-container {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.other-avatar-container{
		margin-bottom: 50rpx;
	}
	.status-container {
		margin-top: 100rpx;
	}
</style>
