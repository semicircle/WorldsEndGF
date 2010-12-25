import urllib
import sys
import fetchNews
import httplib
import time

def go():
    newslist = fetchNews.fetch_news()
    conn = httplib.HTTPConnection("semicircle-test.appspot.com")
    #conn = httplib.HTTPConnection("localhost:8080")
    conn.request("GET", "/CleanNews")
    #time.sleep(5)
    rsp = conn.getresponse()
    if (200 != rsp.status):
        print rsp.status, rsp.reason
        return
    #time.sleep(5)
    for item in newslist:
        encpyLink = urllib.quote(item[1])
        conn = httplib.HTTPConnection("semicircle-test.appspot.com")
        params = urllib.urlencode({'title':item[0], 'link':encpyLink})
        conn.request("POST", "/AddNews", params)
        #time.sleep(5)
        rsp = conn.getresponse()
        #time.sleep(5)
        if (200 != rsp.status):
            print rsp.status, rsp.reason
            break
        else:
            print "updating: " + item[0]
        pass
    pass

go()




    
