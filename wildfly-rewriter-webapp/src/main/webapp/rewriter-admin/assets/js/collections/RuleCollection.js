
define([
  'backbone',
  'models/RuleModel'
  ], function(Backbone, RuleModel) {
  
  var RuleCollection = Backbone.Collection.extend({
    model : RuleModel,
    url : CONTEXT_ROOT + "/api/rules",
    parse : function(data) {
      return data.rule;
    }
  });
  
  // Only want one instance of the collection
  return new RuleCollection();
});
