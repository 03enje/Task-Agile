import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import registrationService from '@/services/registration'

describe('services/registration', () => {
  let mock

  beforeEach(() => {
    mock = new MockAdapter(axios)
  })

  afterAll(() => {
    mock.restore()
  })

  it('register 메소드 HTTP POST 요청 성공 테스트', () => {
    mock.onPost('/registrations').reply(200, { result: 'success' })

    return registrationService.register().then(data => {
      expect(data.result).toEqual('success')
    })
  })

  it('register 메소드 HTTP POST 요청 실패 테스트', () => {
    mock.onPost('/registrations').reply(400, { message: 'Bad request' })

    return registrationService.register().catch(error => {
      expect(error.response.data.message).toEqual('Bad request')
    })
  })
})
