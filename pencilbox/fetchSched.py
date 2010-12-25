# -*- coding: UTF-8 -*-
import sys
import urllib
import urllib2
import httplib
import HTMLParser
import re
from BeautifulSoup import BeautifulSoup
from datetime import *
from time import *

class SohuSchedParser(HTMLParser.HTMLParser):
    schedDate = ''
    isInSchday = False
    def handle_starttag(self, tag, attrs):
        if ('div' == tag) and ('class' in attrs) and ('schday' == attrs['class']) :
            self.isInSchday = True
        pass
    def handle_endtag(self, tag):
        if ('div' == tag) and (self.isInSchday):
            self.isInSchday = False
        pass
    def handle_data(self, data):
        if (self.isInSchday):
            print data
            self.schedDate = data
        pass
    pass

class SchedItem:
    title=''
    hostTeam=''
    visitTeam=''
    startTime=''
    endTime=''
    link=''
    pass


def fetch_sched_by_round(round_num):
    #content = urllib.urlopen('http://stats.sports.sohu.com/schedule.aspx?lega=seri&r=13').read()
    url = 'http://stats.sports.tom.com/schedule.aspx?lega=seri&r=' + str(round_num)
    content = urllib2.urlopen(url).read()
    #content = unicode(content_src, 'UTF-8')
    #parser = SohuSchedParser()
    soup = BeautifulSoup(content, fromEncoding="GB18030")
    ret = []
    for div_sched in soup.findAll(attrs={'class':re.compile("schday")}):
        #print div_sched
        #print div_sched.next.next.next
        div_shtdm = div_sched.nextSibling
        day = div_sched.next.next.next
        day = day[5:-4]
        #tr_titleLine = div_shtdm.next
        #tr_firstContentLine = tr_titleLine.nextSibling
        for tr_line in div_shtdm.contents[0].contents[1:]:
            #print tr_line
            #time / host / host logo/ result / visit logo / visit
            sch_time = tr_line.contents[0].next
            sch_host = tr_line.contents[1].next.next
            sch_result = tr_line.contents[3].next.next
            sch_visit = tr_line.contents[5].next.next
            sch_title = str(day) + ' ' + str(sch_time) + ' ' + str(sch_host) + 'vs' + str(sch_visit) 
            #print sch_title
            item = SchedItem()
            item.title = sch_title
            item.hostTeam = sch_host
            item.visitTeam = sch_visit

            #so, seize the day
            daystring = day + ' '  + str(sch_time)
            dt = datetime.strptime(daystring, "%Y-%m-%d %H:%M")
            item.startTime = str(int(mktime(dt.timetuple()))) + '000'
            item.endTime = str(int(mktime(dt.timetuple())) + 6000) + '000'

            ret.append(item)

            pass
        pass
    pass

    return ret

    #parser.feed(content)
    #parser.close()
    pass

def fetch_sched(team_spec):
    ret = []
    for i in range(1, 39):
        print 'round: ' + str(i)
        all_match_round = fetch_sched_by_round(i)
        for item in all_match_round:
            if ((item.hostTeam == team_spec) or (item.visitTeam == team_spec)):
                ret.append(item)
                print item.title
            pass
        pass
    return ret

#fetch_sched(u'AC米兰')
