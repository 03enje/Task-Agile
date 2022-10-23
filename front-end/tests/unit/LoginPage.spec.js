import { shallowMount } from '@vue/test-utils'
import LoginPage from '@/views/LoginPage.vue'

describe('LoginPage.vue', () => {
  it('should render correct contents', () => {
    const msg = 'TaskAgile'
    const wrapper = shallowMount(LoginPage, {
      props: { msg }
    })
    expect(wrapper.find('h1').text()).toEqual(msg)
  })
})
