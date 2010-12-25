import cgi
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
import urllib
from google.appengine.ext import db
from google.appengine.ext.db import polymodel

from app_data_model import *

class DefaultPage(webapp.RequestHandler):
    def post(self):
        self.response.headers['Content-Type'] = 'text/plain'
        self.response.out.write('<message>[post]Hello, semicircle_test is still'
                                'alive.</message>')
        pass
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
        #self.response.out.write('<message>[post]Hello, semicircle_test is still'
        #                        'alive.</message>')
        self.response.out.write('<?xml version="1.0" encoding="UTF-8" ?> \n')
        self.response.out.write('<newsitems>\n')
        query = NewsRecord.all()
        for item in query:
            self.response.out.write('<newsitem>\n'
                                    '<title>' + item.title + '</title>\n'
                                    '<link>' + item.link + '</link>\n'
                                    '</newsitem>\n')
            pass
        self.response.out.write('</newsitems>')
        pass
    pass

class QueryPage(webapp.RequestHandler):
    def get(self):
        self.response.headers['Context-Type'] = 'text/plain'
        user = self.request.get("user")
        userip = self.request.get("userip")
        lookingfor = self.request.get("lookingfor")
        
        #search for lookingfor
        #if (targetuser is not None):
            #return target userip
            #delete target userip
        #else:
            #add current user to the datastore

        pass
    pass

class SchedPage(webapp.RequestHandler):
    def get(self):
        self.response.headers['Context-Type'] = 'text/plain'
        query = SchedRecord.all()
        query.order("starttime")
        self.response.out.write('<scheditems>')
        for item in query:
            self.response.out.write('<scheditem>'
                                    '<title>' + item.title + '</title>'
                                    '<link>' + item.link + '</link>'
                                    '<hostteam>' + item.hostteam + '</hostteam>'
                                    '<visitteam>' + item.visitteam + '</visitteam>'
                                    '<starttime>' + item.starttime + '</starttime>'
                                    '<endtime>' + item.endtime + '</endtime>'
                                    '</scheditem>')
            pass
        self.response.out.write('</scheditems>')

class AddNewsPage(webapp.RequestHandler):
    def post(self):
        self.response.headers['Context-Type'] = 'text/plain'
        _title = self.request.get("title")
        _link = self.request.get("link")
        n = NewsRecord(title = _title, link = _link)
        n.put()
    pass

class CleanNewsPage(webapp.RequestHandler):
    def get(self):
        self.response.headers['Context-Type'] = 'text/plain'
        query = NewsRecord.all()
        for item in query:
            item.delete()
        pass
    pass

class AddSchedPage(webapp.RequestHandler):
    def post(self):
        self.response.headers['Context-Type'] = 'text/plain'
        _title = self.request.get("title")
        _link = self.request.get("link")
        _hostteam = self.request.get("hostteam")
        _visitteam = self.request.get("visitteam")
        _starttime = self.request.get("starttime")
        _endtime = self.request.get("endtime")
        s = SchedRecord(title = _title, 
                        link = _link, 
                        hostteam = _hostteam,
                        visitteam = _visitteam,
                        starttime = _starttime,
                        endtime = _endtime)
        s.put()
    pass

class CleanSchedPage(webapp.RequestHandler):
    def get(self):
        self.response.headers['Context-Type'] = 'text/plain'
        query = SchedRecord.all()
        for item in query:
            item.delete()
        pass
    pass

application = webapp.WSGIApplication([('/', DefaultPage),
                                      ('/Sched',SchedPage),
                                      ('/AddNews',AddNewsPage),
                                      ('/CleanNews', CleanNewsPage),
                                      ('/AddSched', AddSchedPage),
                                      ('/CleanSched', CleanSchedPage)],
                                     debug=True)

def main():
    run_wsgi_app(application)

if __name__ == '__main__':
    main()

        
        
        

