<template>
<div>
  <apexchart type="radialBar" :height="chartOptions.chart.height" :width="chartOptions.chart.width" :options="chartOptions" :series="series"></apexchart>
</div>
</template>

<script>
  export default {
    name: 'QuizScore',
    data() {
      return {
        isLoading: false,
        series: [85],
        chartOptions: {
          chart: {
            height: 200,
            width: 150,
            type: 'radialBar',
          },
          plotOptions: {
            radialBar: {
              hollow: {
                size: '60%',
              },
            },
          },
          stroke: {
            dashArray: 4,
            width: 0,
          },
          // fill: {
          //   colors: ['#2d8779'],
          // },
          colors: ['#007c49'],
          labels: ['PASS'],
        },
      };
    },
  };
</script>

<style scoped>

</style>
