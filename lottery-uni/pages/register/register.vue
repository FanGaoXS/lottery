<template>
	<view class="page-container">
		<u-toast ref="uToast" />
		<register-page>
			<view slot="body" class="button-container">
				<u-button 
					type="primary" 
					:loading="buttonLoading"
					throttle-time="500"
					:custom-style="buttonStyle"
					:disabled="buttonLoading"
					@click="handleRegisterButton"
					ripple>登记</u-button>
			</view>
		</register-page>
	</view>
</template>

<script>
	
	import registerPage from './registerPage.vue'
	
	import {
		registerUser,
		getRegister,
		getRegisterByMd5
	} from '@/api/user.js'
	
	import {
		setInfo,
		getInfo
	} from '@/utils/info.js'
	
	import {
		getQRCodeQueryParam
	} from '@/utils/url-params.js'
	
	export default{
		 async onLoad({placeId,q}) {
			console.log('页面参数placeId->',placeId);
			console.log('二维码参数q->',q);
			if(placeId!==undefined){ 		//如果直接从页面进入
				this.placeId = placeId
			} else if(q!==undefined){		//如果通过扫码二维码进入（需要从options.q中获取placeId参数）
				this.placeId = getQRCodeQueryParam(decodeURIComponent(q),'placeId') 
			}
			console.log('会场编号->',this.placeId);
			console.log('存储中存在用户个人信息->',this.isUserInfo);
			if(this.isUserInfo){ //storage中存在userInfo
				const { data } = await getRegisterByMd5(this.userInfo.nickName,this.userInfo.avatarUrl,this.placeId)
				if(data!==null){
					this.isRegister = true
					this.routerToSuccess(data)
				}
				console.log('用户是否已经登记->',this.isRegister);
			}
		},
		components: {
			registerPage
		},
		methods: {
			handleRegisterButton() {
				this.buttonLoading = true
				if(this.isUserInfo){
					this.handleRegisterUser()
				} else {
					const that = this
					uni.getUserProfile({
						desc: '需要获取用户昵称和头像',
						success({userInfo}) {
							//获取用户个人信息成功
							console.log('从微信服务器获取用户个人信息成功->',userInfo);
							that.handleUserInfo(userInfo) //将userInfo放置到stroage
							getRegisterByMd5(that.userInfo.nickName,that.userInfo.avatarUrl,that.placeId).then(res=>{
								if(res.data!=null){ //说明用户上次已经登记
									that.isRegister = true
									that.routerToSuccess(res.data) //跳转到登记成功页
								} else {
									that.handleRegisterUser() //登记用户
								}
							})
							
						},
						fail(e) {
							//获取用户个人信息失败
							console.log('获取用户个人信息失败->',e);
						}
					})
				}
			},
			//将用户个人信息放置stroage
			handleUserInfo(userInfo){
				this.userInfo.nickName = userInfo.nickName
				this.userInfo.avatarUrl = userInfo.avatarUrl
				setInfo(userInfo)
			},
			handleRegisterUser(){
				this.buttonLoading = true
				registerUser(this.userInfo,this.placeId).then(res=>{//res.data返回登录成功的数据或者null
					const that = this
					console.log(res.data?'首次登记成功':'首次登记失败',res);
					this.$refs.uToast.show({
						title: res.data?'首次登记成功':'首次登记失败',
						type: res.data?'success':'error',
						duration: '1000',
						callback(){
							if(res.data!==null){
								that.routerToSuccess(res.data)
							}
							that.buttonLoading = false
						}
					})
				}).catch(error=>{
					console.log('登记错误：后端响应错误');
					this.$refs.uToast.show({
						title: '登记错误',
						type: 'error',
						duration: '2000'
					})
					this.buttonLoading = false
				})
			},
			routerToSuccess(registerInfo){
				this.$u.route({
					url: 'pages/register/success',
					type: 'reLaunch',
					params: {
						id: registerInfo.id
					}
				})
			}
		},
		data() {
			return {
				buttonLoading: false,
				buttonStyle:{
					fontSize: '50rpx',
					borderRadius: '100rpx',
					height: '200rpx',
					width: '200rpx',
					boxShadow:'2px 2px 3px #aaaaaa'
				},
				placeId: undefined,
				userInfo:{
					nickName: getInfo().nickName,
					avatarUrl: getInfo().avatarUrl
				},
				isUserInfo: !getInfo()=='',
				isRegister: false
			}
		},
	}
</script>

<style>
	
	.button-container{
		margin: 150rpx 0 150rpx 0;
	}

</style>