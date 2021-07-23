<template>
  <b-card>
    <b-row :no-gutters="true">
      <b-col cols="auto" class="mr-3">
        <i :class="icon" class="card-icon"></i>
      </b-col>
      <b-col>
        <div class="h5">
          <slot name="title">
            {{ title }}
          </slot>
        </div>
        <div class="text-uppercase text-secondary">
          {{ subTitle }}
        </div>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
  export default {
    name: 'MyQuizInfoCard',
    props: {
      title: {
        type: String,
        optional: true,
      },
      subTitle: String,
      icon: String,
    },
  };
</script>

<style scoped>
.card-icon {
  font-size: 3rem;
}
</style>
