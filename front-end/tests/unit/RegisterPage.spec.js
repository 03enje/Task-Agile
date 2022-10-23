import { shallowMount } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage.vue'

describe('RegisterPage.vue', () => {
  it('should render correct contents', () => {
    const wrapper = shallowMount(RegisterPage)
    expect(wrapper.find('.logo').attributes('src')).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Open source task management tool')
    expect(wrapper.find('#username').element.value).toEqual('')
    expect(wrapper.find('#emailAddress').element.value).toEqual('')
    expect(wrapper.find('#password').element.value).toEqual('')
    expect(wrapper.find('form button[type="submit"]').text()).toEqual('Create account')
  })
})
