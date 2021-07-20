<template>
  <div>Acme table </div>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class AcmeTable extends Vue {
  @Prop() private data!: Array;
}
</script>