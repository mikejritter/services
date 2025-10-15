This is for tracking major dependency requirements between our dependencies, e.g. servlet api version between us, nuxeo, and spring.

idk how to format this doc
todo: include resteasy?

# Tomcat Version

| Nuxeo | CSpace |
|-------| ------ |
| 9.0.106|  8    |

## Servlet Spec Support

| Tomcat | CSpace | Spring 5 |
|--------|--------|----------|
| 4 (javaee) | ?  |  3.1+    |

## Persistence Versions

|                  | CSpace | Nuxeo | Spring 5 |
|----------------- | ------ | ----- | -------- |
|javax.transaction |   ?    | 1.3   |    ?     |
|javax.validation  |   ?    | 1.1.0 |    ?     |

## WS Versions

|            | CSpace | Nuxeo | Spring 5 |
|------------| ------ | ----- | -------- |
|   bind     |   ?    | 2.3.3 |    ?     |
|   xml.ws   |   ?    | 2.3.1 |    ?     |
