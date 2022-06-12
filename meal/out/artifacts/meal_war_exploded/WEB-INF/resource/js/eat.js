const cate = document.querySelector('.category');
const cateBtn = cate.querySelectorAll('label');
const foods = document.querySelectorAll('.food');
const rating = document.querySelector('.rating-star');
const ratingStar = rating.querySelectorAll('.star');
const ratingBtn = rating.querySelectorAll('input[type=radio]');

function toggleActive() {
    if (!this.classList.contains('a-fix')) {
        this.classList.toggle('active');
    }
}

function activeFix() {
    cateBtn.forEach(btn => {
        btn.classList.remove('a-fix');
        btn.classList.remove('active');
    })
    this.classList.add('a-fix');
    this.classList.add('active');
}

function colorStar(num = 0) {
    for (let i =0; i< 5; i++) {
        if (i <= num) {
            ratingStar.item(i).classList.add('yellow');
        } else {
            ratingStar.item(i).classList.remove('yellow');
        }
    }

    if (this.event.type === 'mouseleave') {
        for (let i =0; i< 5; i++) {
            if (ratingStar.item(i).classList.contains('fixedStar')) {
                ratingStar.item(i).classList.add('yellow');
            }
        }
    }
}

function fixStar(num = 0) {
    for (let i =0; i< 5; i++) {
        if (i <= num) {
            ratingStar.item(i).classList.add('fixedStar');
        } else {
            ratingStar.item(i).classList.remove('fixedStar');
        }
    }
}

// 카테고리 이벤트
cateBtn.forEach(btn => btn.addEventListener('mouseenter',toggleActive))
cateBtn.forEach(btn => btn.addEventListener('mouseleave',toggleActive))
cateBtn.forEach(btn => btn.addEventListener('click',activeFix))

// 음식 선택 이벤트
foods.forEach(food =>
    food.querySelector('.food-name').addEventListener('mouseenter',toggleActive))
foods.forEach(food =>
    food.querySelector('.food-name').addEventListener('mouseleave',toggleActive))

// 별 평가 이벤트
ratingBtn.forEach((rate,i) =>rate.addEventListener('click',() => fixStar(i)))
ratingStar.forEach((rate,i) =>rate.addEventListener('mouseenter',() => colorStar(i)))
ratingStar.forEach((rate,i) =>rate.addEventListener('mouseleave',() => colorStar(-1)))