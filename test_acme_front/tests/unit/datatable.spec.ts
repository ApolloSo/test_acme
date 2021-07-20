import { createLocalVue, mount } from "@vue/test-utils";
import AcmeTable from "@/components/AcmeTable.vue";

import Vue from 'vue'
import Vuetify from 'vuetify'
Vue.use(Vuetify)

describe("AcmeTable.vue", () => {
  const localVue = createLocalVue()
  const vuetify = new Vuetify()
  // beforeEach(() => {
  //   vuetify: new Vuetify()
  // })

  it("renders props.data when passed", () => {
    const data = [{name: 'test'}]
    const wrapper = mount(AcmeTable, {
      localVue,
      vuetify,
      propsData: { data },
    });
    // expect(wrapper.find('test').exists()).toBe(true)
    expect(wrapper.html()).toMatchSnapshot()
    const cell = wrapper.find('tbody > tr > td')
    expect(cell.text()).toBe('test')
  });
});
