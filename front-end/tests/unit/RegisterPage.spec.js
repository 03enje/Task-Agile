import { flushPromises, shallowMount } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage.vue'
import registrationService from '@/services/registration'

jest.mock('@/services/registration')
describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit
  let registerSpy

  beforeEach(() => {
    const mockRoute = {
      params: {
        id: 1
      }
    }
    const mockRouter = {
      push: jest.fn()
    }

    registerSpy = jest.spyOn(registrationService, 'register')

    wrapper = shallowMount(RegisterPage, {
      props: {
        isAuthenticated: true
      },
      global: {
        mocks: {
          $route: mockRoute,
          $router: mockRouter
        }
      }
    })

    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
  })

  afterEach(() => {
    registerSpy.mockReset()
    registerSpy.mockRestore()
  })

  afterAll(() => {
    jest.restoreAllMocks()
  })

  it('회원가입 폼 요소들의 존재여부 테스트', () => {
    const wrapper = shallowMount(RegisterPage)
    expect(wrapper.find('.logo').attributes('src')).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create account')
  })

  it('데이터 모델의 초깃값 테스트', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('데이터 모델과 폼 입력 필드 간의 바인딩 테스트', async () => {
    const username = 'sunny'
    const emailAddress = 'sunny@local'
    const password = 'VueJsRocks!'

    wrapper.setData({
      form: {
        username: username,
        emailAddress: emailAddress,
        password: password
      }
    })

    await flushPromises()
    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldEmailAddress.element.value).toEqual(emailAddress)
    expect(fieldPassword.element.value).toEqual(password)
  })

  it('폼 이벤트 핸들러의 존재 여부 테스트', () => {
    const stub = jest.spyOn(wrapper.vm, 'submitForm')
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('회원가입 성공 검증 테스트', async () => {
    expect.assertions(2)
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.emailAddress = 'sunny@taskagile.com'
    wrapper.vm.form.password = 'JestRocks!'
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await flushPromises()
    expect(stub).toHaveBeenCalledWith({ name: 'LoginPage' })
  })

  it('회원가입 실패 검증 테스트', async () => {
    expect.assertions(3)
    wrapper.setData({
      form: {
        username: 'ted',
        emailAddress: 'ted@taskagile.com',
        password: 'JestRocks!'
      }
    })
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await flushPromises()
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  })

  it('이메일 주소 값 유효하지 않을 시 실패 테스트', () => {
    wrapper.setData({
      form: {
        username: 'test',
        emailAddress: 'bad-email-address',
        password: 'JestRocks!'
      }
    })
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })

  it('이름 값 유효하지 않을 시 실패 테스트', () => {
    wrapper.setData({
      form: {
        username: 'a',
        emailAddress: 'test@taskagile.com',
        password: 'JestRocks!'
      }
    })
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })

  it('비밀번호 값 유효하지 않을 시 실패 테스트', () => {
    wrapper.setData({
      form: {
        username: 'test',
        emailAddress: 'test@taskagile.com',
        password: 'bad!!'
      }
    })
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })
})
