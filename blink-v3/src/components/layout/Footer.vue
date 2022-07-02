<template>
  <footer class="position-static bottom-0 w-100 p-5 bg-primary text-white text-center position-relative" id="kopirajt">
    <div class="container">
      <p class="lead mb-0">Copyright &copy; 2022. Sva prava zadržana</p>
      <a id="back-to-top" href="#" class="back-to-top text-secondary h1" role="button"><i class="bi bi-arrow-up-square-fill"></i></a>
    </div>
  </footer>
</template>

<style>
.back-to-top {
    position: fixed;
    bottom: 27px;
    right: 27px;
    display: none;
}
</style>

<script>
import $ from "jquery";

$(document).ready(function(){
	$(window).scroll(function () {
			if ($(this).scrollTop() > 50) {
				$('#back-to-top').fadeIn();
			} else {
				$('#back-to-top').fadeOut();
			}
		});
		// scroll body to 0px on click
		$('#back-to-top').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 300);
			return false;
		});
});

export default {
  name: "FooteR"
};
</script>