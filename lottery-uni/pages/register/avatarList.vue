<template>
	<view class="avatar-list-container">
		<u-row>
			<u-col span="4" text-align="right"> 
				<span class="avatar-container">
					<u-avatar size="60" mode="square" :src="userList[0].avatar"></u-avatar>
				</span>
				<span></span>
				<span></span>
				<span></span>
			</u-col>
			<u-col span="4" text-align="center"> 
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[1].avatar"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
			</u-col>
			<u-col span="4" text-align="left"> 
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[2].avatar"></u-avatar>
			</u-col>
		</u-row>
		<u-row>
			<u-col span="4" text-align="right">
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[3].avatar"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
			</u-col>
			<u-col span="4" text-align="center"> 
				<u-avatar size="120" mode="square" :src="center.avatar"></u-avatar>
			</u-col>
			<u-col span="4" text-align="left"> 
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[4].avatar"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
			</u-col>
		</u-row>
		<u-row>
			<u-col span="4" text-align="right"> 
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[5].avatar"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
			</u-col>
			<u-col span="4" text-align="center"> 
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[6].avatar"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
			</u-col>
			<u-col span="4" text-align="left"> 
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square" :src="userList[7].avatar"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
				<u-avatar size="60" mode="square"></u-avatar>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		props: {
			center: {
				type: Object,
				default(){
					return {}
				}
			},
			userList: {
				type: Array,
				default() {
					return []
				}
			}
		},
		data() {
			return {}
		},
		methods: {
			name() {
				
			}
		},
	}
</script>

<style>
	
	.avatar-list-container{

	}
	
</style>
