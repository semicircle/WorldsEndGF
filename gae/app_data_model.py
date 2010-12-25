from google.appengine.ext import db
from google.appengine.ext.db import polymodel

class NewsRecord(polymodel.PolyModel):
    title = db.StringProperty()
    link = db.StringProperty()
    pass

class SchedRecord(polymodel.PolyModel):
    title = db.StringProperty()
    link = db.StringProperty()
    hostteam = db.StringProperty()
    visitteam = db.StringProperty()
    starttime = db.StringProperty()
    endtime = db.StringProperty()
    pass

