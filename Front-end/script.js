const btn = document.querySelector('#zapocni');
const close_btn = document.querySelector('#close-button')
const popUp = document.querySelector('.pop-up');

btn.addEventListener('click', () => {
    popUp.classList.add('pop-up__toggle');
});

close_btn.addEventListener('click', () => {
    popUp.classList.remove('pop-up__toggle');
});

