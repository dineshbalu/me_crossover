"use strict";
angular.module('bandcampApp.config', [])
.constant('ENV', 'production')

.constant('baseUrl', 'https://api.crossover.com/api')

.constant('clientUrl', 'https://app.crossover.com/x')

.constant('storageBaseUrl', 'https://s3.amazonaws.com/')

.constant('externalUrl', undefined)

.constant('routePrefix', 'views/')

.constant('uploadedDocsUrl', 'https://crossover-uploads-production.s3.amazonaws.com')

.constant('linkedInApiKey', '787fo93vvlysh1')

.constant('intercomKey', 'w4ev5r2t')

.constant('intercomContractorAppId', 'th0oxd5l')

.constant('intercomVisitorAppId', 'th0oxd5l')

.constant('skypeIdRegex', '^([A-Za-z]([A-Za-z0-9\\._\\-\\+@:]){5,99})$')

.constant('googleCalendarIntegrationSettings', {PLATFORM_JS:'https://apis.google.com/js/platform.js',CLIENT_JS:'https://apis.google.com/js/client.js',CLIENT_ID:'199971004081-bnftmhshsqdifpgl3qcsnhjnb8sovddk.apps.googleusercontent.com',SCOPES:['https://www.googleapis.com/auth/userinfo.email','https://www.googleapis.com/auth/calendar.readonly'],IMMEDIATE:false,DOMAIN:'crossover.com',AUTH_USER:-1,ACCESS_TYPE:'offline',RESPONSE_TYPE:'code token',APPROVAL_PROMPT:'force'})

.constant('techScreenUrl', 'http://techscreen.crossover.com/')

.constant('verifyCredentialsUrl', 'https://secure.safesthires.com/draftpick.php')

.constant('credentialPoliciesUrl', 'http://safesthires.com/compliance')

.constant('credentialsDownloadUrl','https://docs.google.com/document/d/1vyd5aMQ-mWyzD3ugmnM0_E3CGMdlatyQJRNcRdjX8ws/edit')

.constant('crossoverProductionUrl', {"people" : "https://www.crossover.com/people/",
    "playbooks" : "https://www.crossover.com/playbooks/",
    "productivity" : "https://www.crossover.com/productivity/",
    "forCandidates" : "https://www.crossover.com/for-candidates/",
    "aboutUs" : "https://www.crossover.com/about-us/",
    "blog" : "http://blog.crossover.com",
    "contactUs" : "https://www.crossover.com/contact-us/"})