import { shallowMount } from '@vue/test-utils'
import LoginPage from '@/views/LoginPage.vue'

describe('LoginPage.vue', () => {
  it('올바른 콘텐츠 제공', () => {
    const msg = 'TaskAgile'
    const wrapper = shallowMount(LoginPage, {
      props: { msg }
    })
    expect(wrapper.find('h1').text()).toEqual(msg)
  })
})
