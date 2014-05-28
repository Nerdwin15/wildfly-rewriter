
define(['backbone'], function(Backbone) {

  var RewriteRuleModel = Backbone.Model.extend({
    urlRoot : CONTEXT_ROOT + "/api/rules",
  });
  
  return RewriteRuleModel;
});
