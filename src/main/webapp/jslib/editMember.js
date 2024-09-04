/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('editMemberForm');
    const profilePicInput = document.getElementById('profilePic');
    const currentProfilePic = document.querySelector('img[alt="Current Profile Picture"]');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        if (validateForm()) {
            form.submit();
        }
    });

    profilePicInput.addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                if (currentProfilePic) {
                    currentProfilePic.src = e.target.result;
                } else {
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.alt = "New Profile Picture";
                    img.className = "mt-2 w-32 h-32 object-cover rounded-full";
                    profilePicInput.parentNode.appendChild(img);
                }
            }
            reader.readAsDataURL(file);
        }
    });

    function validateForm() {
        let isValid = true;
        const name = document.getElementById('name').value.trim();
        const email = document.getElementById('email').value.trim();
        const phone = document.getElementById('phone').value.trim();

        // 驗證姓名
        if (name === '') {
            alert('請輸入姓名');
            isValid = false;
        }

        // 驗證電子郵件
        if (email === '' || !isValidEmail(email)) {
            alert('請輸入有效的電子郵件地址');
            isValid = false;
        }

        // 驗證電話號碼（如果有輸入的話）
        if (phone !== '' && !isValidPhone(phone)) {
            alert('請輸入有效的電話號碼');
            isValid = false;
        }

        return isValid;
    }

    function isValidEmail(email) {
        const re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        return re.test(email);
    }

    function isValidPhone(phone) {
        const re = /^[0-9]{10}$/;
        return re.test(phone);
    }
});