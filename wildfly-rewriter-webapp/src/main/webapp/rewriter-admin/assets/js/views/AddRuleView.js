
define([
  'backbone',
  'handlebars',
  'jquery',
  'text!templates/addRule.xhtml',
  'collections/RuleCollection'
  ], function(Backbone, Handlebars, $, rawTemplate, RuleCollection) {
  
  var template = Handlebars.compile(rawTemplate);
  
  var AddRuleView = Backbone.View.extend({
    
    $fromField : null,
    $toField : null,
    
    initialize : function() {
      this.bind("ok", this.okClicked);
    },
    
    render : function() {
      this.$el.html( template(this.model.toJSON()) );
      this.$fromField = this.$("#from");
      this.$toField = this.$("#to");
      return this;
    },
    
    okClicked : function(modal) {
      modal.preventClose();
      
      this.model.set('to', $.trim(this.$toField.val()));
      this.model.set('from', $.trim(this.$fromField.val()));
      if (this.model.get('to') == '' || this.model.get('from') == '') {
        return;
      }

      this.model.save({}, { 
        success : function(model) {
          RuleCollection.add(model);
          modal.close();
        },
        error : function() {
          console.log("UH OH");
        }
      });
    },
    
    getSubmitButtonText : function() { return "Add Rule"; },
    getCancelButtonText : function() { return "Cancel"; }
  
  });
  
  return AddRuleView;
});
