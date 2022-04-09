const btn = document.querySelector('#zapocni');
const close_btn = document.querySelector('#close-button')
const popUp = document.querySelector('.pop-up');
const navbar =  document.querySelector('.navbar');
const menu = document.querySelector('.menu-bar');

btn.addEventListener('click', () => {
    popUp.classList.add('pop-up__toggle');
    document.body.style.background = 'linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(images/background-pattern.png)';
    navbar.style.backgroundColor = '#4c3914';
    navbar.style.pointerEvents = 'none';
    navbar.style.pointerEvents = 'none';
    btn.style.color = '#4c4c4c';
});

close_btn.addEventListener('click', () => {
    navbar.style.pointerEvents = '';
    navbar.style.pointerEvents = '';
    document.body.style.background = '';
    navbar.style.backgroundColor = '';
    popUp.classList.remove('pop-up__toggle');
    btn.style.color = '';
});

// background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(https://images.unsplash.com/photo-1614030424754-24d0eebd46b2);
