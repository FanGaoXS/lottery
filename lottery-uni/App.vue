<script>
	export default {
		onLaunch: function() {
			console.log('App打开')
		},
		onShow: function() {
			console.log('App前台显示')
		},
		onHide: function() {
			console.log('App后台挂起')
		}
	}
</script>

<style lang="scss">
	/* 注意要写在第一行，同时给style标签加入lang="scss"属性 */
	@import "uview-ui/index.scss";
</style>
