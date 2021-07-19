<template>
	<view>
		<u-no-network></u-no-network>
		<u-card 
			margin="200rpx 80rpx 0 80rpx" 
			box-shadow="2px 2px 3px #aaaaaa"
			:show-head="showHead" 
			:show-foot="showFoot"
			:title="title"
			title-size="35"
			padding="50" >
			<view class="card-body" slot="body">
				<slot name="body"></slot>
			</view>
			<view class="card-foot" slot="foot">
				<slot name="foot"></slot>
			</view>
		</u-card>
	</view>
</template>

<script>
	
	export default {
		props: {
			showHead: {
				type: Boolean,
				default: false
			},
			showFoot: {
				type: Boolean,
				default: false
			},
			title: {
				type: String,
				default: '会场'
			}
		}
	}
	
</script>

<style>
	
	page{
		background-size:750rpx;
		background-image: url('~@/static/cow3.webp');
	}
	
	.card-body{
		margin: 100rpx 0 100rpx 0;
	}
	
	.card-foot{
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	
</style>
