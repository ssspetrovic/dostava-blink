const btn = document.querySelector('#zapocni');
const close_btn = document.querySelector('#close-button')
const popUp = document.querySelector('.pop-up');
const navbar =  document.querySelector('.navbar')

btn.addEventListener('click', () => {
    popUp.classList.add('pop-up__toggle');
    document.body.style.background = 'linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(images/background-pattern.png)';
    navbar.style.backgroundColor = '#4c3914';
});

close_btn.addEventListener('click', () => {
    document.body.style.background = '';
    navbar.style.backgroundColor = '';
    popUp.classList.remove('pop-up__toggle');
});

// background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(https://images.unsplash.com/photo-1614030424754-24d0eebd46b2);
