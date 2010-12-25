# -*- coding:UTF-8 -*-
import urllib
import sys
import fetchSched
import httplib
import time

go_url = "semicircle-test.appspot.com"
#go_url = "localhost:8080"

def go():
    schedlist = fetchSched.fetch_sched(u'AC米兰')
    conn = httplib.HTTPConnection(go_url)
    #conn = httplib.HTTPConnection("localhost:8080")
    conn.request("GET", "/CleanSched")
    #time.sleep(5)
    rsp = conn.getresponse()
    if (200 != rsp.status):
        print rsp.status, rsp.reason
        return
    #time.sleep(5)
    for item in schedlist:
        conn = httplib.HTTPConnection(go_url)
        #print item.startTime
        params = urllib.urlencode(
            {'title':item.title, 
             'link':'',
             'hostteam':item.hostTeam,
             'visitteam':item.visitTeam,
             'starttime':item.startTime,
             'endtime':item.endTime})
        conn.request("POST", "/AddSched", params)
        #time.sleep(5)
        rsp = conn.getresponse()
        #time.sleep(5)
        if (200 != rsp.status):
            print rsp.status, rsp.reason
            break
        else:
            print "updating: " + item.title
        pass
    pass

go()




    
