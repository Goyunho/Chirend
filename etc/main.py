import random
import requests

def ranParams():
    params = {
        'age' : 0,
        'sex' : 0,
        'contents_id' : 1
    }
    params['age'] = random.randint(3, 13)
    params['sex'] = random.randint(0, 1)
    params['contents_id'] = random.randint(1, 6)

    return params

def main() :
    url = 'http://naneg93.dothome.co.kr'

    for i in range(100):
        resp = requests.get(url, params=ranParams())
        if('002' in resp.text):
            print('Error! \t 쿼리를 확인해주세요!')
            print(resp.text)
        else:
            print(i, 'OK')

if __name__ == '__main__':
    main()