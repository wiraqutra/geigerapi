# Introduction #

This project offers RESTful API. The data format is JSON.

But CSV data format downloading  is also supported.

# Rest Details #
**{resource}** is now only "_radiation_".

**{data group}** is "_min_", "_forecast_", "_detail_","_notes_" or "_full_".

## JSON ##
| | Method | Other Requirement | Response Code | Response Body |
|:|:-------|:------------------|:--------------|:--------------|
| Create | POST /rest/{resource}/{data group}.json | Request Header "Content-Type application/json" and Request Body(JSON Format)| 201 _(created)_ **or** 304 _(duplicated data found)_ **or** 401 _(unauthorized)_| Created One   |
| Read All | GET  /rest/{resource}/{data group}.json | -                 | 200           | All Records   |
| Read One | GET  /rest/{resource}/{data group}/{id}.json | -                 | 200 _(successfull)_ **or** 404 _(id not found)_ | One Record    |
| Update | PUT  /rest/{resource}/{data group}/{id}.json | Request Header "Content-Type application/json" and Request Body(JSON Format) | 201 _(changed)_ **or** 304 _(duplicated data found)_ **or** 404 _(id not found)_ **or** 401 _(unauthorized)_ **or** 403 _(not owned)_| Updated one   |
| Delete | DELETE  /rest/{resource}/{data group}/{id}.json | -                 | 200 _(deleted)_ **or** 404 _(id not found)_ **or** 401 _(unauthorized)_ **or** 403 _(not owned)_| Deleted one   |

### Data Format ###
**Upload Example:**
```

{"hidden":true,"altitude":"10.0","calibrated":"false","counterinfo":"AAAA","countertime":"60.0","datetime":"2011-06-09 07:18:47.692","environment":"0","height":"0.6","humidity":"30.6","id":"15003","imageurl":"http://picasa.google.com/xxxx","label":"JSON_TEST","lat":"37.524523","lon":"139.938825","publicinfo":"false","radiovalue":"93.0","tags":"テスト","temperature":"20.0","terminalinfo":"Xperia","trnsname":"Keisuke Seki","valuepos":"0","valuetype":"0","weather":"1","winddirection":"5","windvelocity":"3.4"}

```

**All Download Example:**
```

{"radiation":[{"datetime":"2011-06-08 07:11:47.691","hidden":"false","id":"9","label":"Hack4Geiger","lat":"0.0","lon":"0.0","radiovalue":"93.0","valuetype":"0"},{"datetime":"2011-06-08 07:11:47.691","hidden":"false","id":"10","label":"Hack4Geiger","lat":"0.0","lon":"0.0","radiovalue":"93.0","valuetype":"0"}]}

```


**One Download Example:**
```

{"datetime":"2011-06-08 07:11:47.691","hidden":"false","id":"9","label":"Hack4Geiger","lat":"0.0","lon":"0.0","radiovalue":"93.0","valuetype":"0"}

```
## CSV ##

| | Method | Other Requirement | Response Code | Response Body |
|:|:-------|:------------------|:--------------|:--------------|
| Create | Not supported yet ! | -                 | -             | -             |
| Read All | GET  /rest/{resource}/{data group}.csv | -                 | 200           | All Records   |
| Read One | GET  /rest/{resource}/{data group}/{id}.csv | -                 | 200 _(successfull)_ **or** 404 _(id not found)_ | One Record    |
| Update | Not supported yet ! | -                 | -             | -             |
| Delete |  Not supported yet ! | -                 | -             | -             |

# Data Schema #

# Reference #

https://docs.google.com/present/edit?id=0Ad3729ke2xCFZGdqMzgzdmtfMTk4Zm12d2Y1Y2Q&hl=ja