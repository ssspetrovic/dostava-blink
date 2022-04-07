
var button = document.getElementById('zapocni');
var close_button = document.getElementById('close-button');
var div = document.getElementsByClassName('pop-up');
var background = document.getElementById

button.addEventListener('click', () => {
    div[0].style.display = "block";
});

close_button.addEventListener('click', () => {
    div[0].style.display = "none";
});
