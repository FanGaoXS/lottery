<template>
	<view class="page-container">
		<u-top-tips ref="uTips"></u-top-tips>
		<register-page 
			:title="place.name"
			:show-foot="true"
			:show-head="true">
			<view slot="body" class="body-container">
				<!-- 微信头像 -->
				<view class="avatar-container">
					<u-avatar size="large" mode="square" :src="userInfo.avatar"></u-avatar>
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
		getRegisterById
	} from '@/api/user.js'

	
	import {
		registerPage
	} from './registerPage'
	
	
	export default {
		filters: {
			timeFilter: function(value) {
				if(value===undefined) return ''
				return new Date(value).toLocaleString();
			}
		},
		components: {
			registerPage
		},
		onLoad({id}) { //页面一加载就获取登记的编号
			this.id = id
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
						this.place.name = res.data.place.name
						this.place.address = res.data.place.address
					}
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
					name: '',
					address: ''
				}
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
	.status-container {
		margin-top: 100rpx;
	}
</style>
