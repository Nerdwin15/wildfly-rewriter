require.config({
  paths: {
    jquery : "lib/jquery.min",
    underscore: "lib/underscore.min",
    backbone: "lib/backbone.min",
    text: "lib/requirejs-text",
    bootstrap : "lib/bootstrap.min",
    handlebars : "lib/handlebars.min",
    bootstrapModal : "lib/backbone.bootstrap-modal"
  },
  shim: {
    underscore: {
      exports: "_"
    },
    backbone: {
      deps: ["underscore", "jquery"],
      exports: "Backbone"
    },
    bootstrap : {
      deps : ["jquery"],
      exports: "jQuery"
    },
    handlebars : {
      exports : "Handlebars"
    },
    bootstrapModal : {
      deps : ["jquery", "backbone", "underscore"]
    }
  }
});

require([
  "jquery",
  "bootstrap"  // Does own initialization
  ], function($, bootstrap) {
  
    $.ajaxSetup({
      cache : false
    });
  
    require([ window.initScript ], function(App) { });
});
