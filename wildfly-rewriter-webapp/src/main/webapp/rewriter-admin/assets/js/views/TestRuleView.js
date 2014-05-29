
define([
  'backbone',
  'handlebars',
  'jquery',
  'text!templates/testRewriteRule.xhtml',
  'collections/RuleCollection'
  ], function(Backbone, Handlebars, $, rawTemplate) {
  
  var template = Handlebars.compile(rawTemplate);
  
  var TestRuleView = Backbone.View.extend({
    
    $fromField : null,
    $loadingIndicator : null,
    $responseWrapper : null,
    timer : null,
    
    events : {
      "keyup form input" : "handleKeyUp"
    },
    
    render : function() {
      this.$el.html( template() );
      this.$fromField = this.$("#from");
      this.$loadingIndicator = this.$("form .glyphicon");
      this.$responseWrapper = this.$("#resolvesTo");
      return this;
    },
    
    handleKeyUp : function() {
      if (this.timer != null)
        clearTimeout(this.timer);
      this.timer = setTimeout($.proxy(this.checkRule, this), 500);
    },
    
    checkRule : function() {
      var route = this.$fromField.val();
      this.$loadingIndicator.addClass("glyphicon-refresh");
      
      $.post(CONTEXT_ROOT + "/api/routing/test", route, $.proxy(this.handleRuleResponse, this));
    },
    
    handleRuleResponse : function(responseBody, result, response) {
      this.$loadingIndicator.removeClass("glyphicon-refresh");
      this.$responseWrapper.text( (response.status == 200) ? responseBody : "--");
    },
    
    getSubmitButtonText : function() { return "Close"; },
    getCancelButtonText : function() { return false; }
  
  });
  
  return TestRuleView;
});
