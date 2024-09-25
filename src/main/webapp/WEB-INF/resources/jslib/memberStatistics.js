$(document).ready(function() {
    $.ajax({
        url: contextPath + '/admin/memberStatistics/data',
        method: 'GET',
        dataType: 'json',
        success: function(statisticsData) {
            createRegistrationTrendChart(statisticsData.registrationTrend);
            createAgeDistributionChart(statisticsData.ageDistribution);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching statistics:", error);
            alert("無法獲取統計數據，請稍後再試。");
        }
    });
});

function createRegistrationTrendChart(data) {
    var ctx = document.getElementById('registrationTrendChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: Object.keys(data),
            datasets: [{
                label: '新會員註冊數量',
                data: Object.values(data),
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function createAgeDistributionChart(data) {
    var ctx = document.getElementById('ageDistributionChart').getContext('2d');
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: Object.keys(data),
            datasets: [{
                label: '年齡分佈',
                data: Object.values(data),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true
        }
    });
}