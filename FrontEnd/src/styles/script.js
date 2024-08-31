
document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.getElementById('menuToggle');
    const navMenu = document.getElementById('menu');

    menuToggle.addEventListener('click', function() {
        menuToggle.classList.toggle('active');
        navMenu.classList.toggle('active');
    });
});

    ScrollReveal().reveal('list',{
        origin: 'left',
        duration: 2000,
        distance: '20%'
    });
    
    ScrollReveal().reveal('section',{
        origin: 'left',
        duration: 2000,
        distance: '20%'
    });

    ScrollReveal().reveal('text',{
        origin: 'left',
        duration: 2000,
        distance: '20%'
    });

    ScrollReveal().reveal('img',{
        origin: 'right',
        duration: 2000,
        distance: '20%'
    });



