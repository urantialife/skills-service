/*
Copyright 2020 SkillTree

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
<template>
  <div class="container-fluid">
    <div class="row justify-content-center text-center" data-cy="resetConfirmation">
    <div class="col col-md-8 col-lg-7 col-xl-4 mt-3" style="min-width: 20rem;">
      <div class="mt-5">
        <logo1 />
        <h3 class="mt-4 text-primary">Password Successfully Reset!</h3>
      </div>
        <div class="card text-left">
          <div class="card-body p-4">
            <p>Your password has been successfully reset! You will be forwarded to the login page in {{ timer }} seconds.</p>
            <div class="text-center">
              <b-button href="/skills-login" variant="outline-primary" class="p-2" data-cy="loginPage"><i class="fas fa-sign-in-alt mr-1"/>Return to Login Page</b-button>
            </div>
          </div>
        </div>

    </div>
  </div>
  </div>
</template>

<script>

  import Logo1 from '../brand/Logo1';

  export default {
    name: 'RequestRestConfirmation',
    components: { Logo1 },
    props: {
      countDown: Number,
    },
    data() {
      return {
        timer: -1,
      };
    },
    mounted() {
      this.timer = this.countDown;
    },
    watch: {
      timer(value) {
        if (value > 0) {
          setTimeout(() => {
            this.timer -= 1;
          }, 1000);
        } else {
          this.$router.push({ name: 'Login' });
        }
      },
    },
  };
</script>
