<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="data"
      :items-per-page="5"
    >
    </v-data-table>
  </v-container>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
@Component
export default class AcmeTable extends Vue {
  @Prop() private data!: Array<any>;
  headers: Array<any> = [
    {
      text: 'Dessert (100g serving)',
      align: 'start',
      sortable: false,
      value: 'name',
    },
    { text: 'Calories', value: 'calories' },
    { text: 'Fat (g)', value: 'fat' },
    { text: 'Carbs (g)', value: 'carbs' },
    { text: 'Protein (g)', value: 'protein' },
    { text: 'Iron (%)', value: 'iron' },
  ]
}
</script>