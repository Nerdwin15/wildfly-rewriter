
define([
  'backbone',
  'handlebars',
  'text!templates/removeRule.xhtml'
  ], function(Backbone, Handlebars, rawTemplate) {
  
  var template = Handlebars.compile(rawTemplate);
  
  var RemoveRuleView = Backbone.View.extend({
    render : function() {
      this.$el.html( template(this.model.toJSON()) );
      return this;
    },
    
    getSubmitButtonText : function() { return "Yes"; },
    getCancelButtonText : function() { return "No/Cancel"; }
  });
  
  return RemoveRuleView;
});
