import random
import requests
import datetime, time

def ranParams():
    params = {
        'age' : 0,
        'sex' : 0,
        'contents_id' : 1
    }
    params['age'] = random.randint(3, 13)
    params['sex'] = random.randint(0, 1)
    params['contents_id'] = random.randint(1, 7)

    return params

def ranParams_ts():
    params = ranParams()
    startTime = datetime.datetime.strptime("2015-01-01 00:00:00", '%Y-%m-%d %H:%M:%S')
    startTime = time.mktime(startTime.timetuple())
    nowTime = time.time()
    timeStamp = random.uniform(startTime, nowTime)
    params['date'] = datetime.datetime.fromtimestamp(timeStamp)
    print(params['date'])
    return params


def main() :
    url = 'http://naneg93.dothome.co.kr/randSetSulmun.php'

    for i in range(10000):
        #resp = requests.get(url, params=ranParams())
        resp = requests.get(url, params=ranParams_ts())
        if('002' in resp.text):
            print('Error! \t 쿼리를 확인해주세요!')
            print(resp.text)
        else:
            print(i, 'OK')

if __name__ == '__main__':
    main()