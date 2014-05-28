
define([
  'backbone',
  'handlebars',
  'jquery',
  'text!templates/ruleRow.xhtml',
  'bootstrapModal'
  ], function(Backbone, Handlebars, $, rawTemplate, bootstrapModal) {
  
  var template = Handlebars.compile(rawTemplate);
  
  var RuleRowView = Backbone.View.extend({
    
    tagName : "tr",
    events : {
      "click .remove" : "handleRemoveClick"
    },
    
    initialize : function() {
      this.listenTo(this.model, "remove", this.removeRule);
    },
    
    render: function() {
      var html = template(this.model.toJSON());
      html = $(html).find("td").unwrap(); // Remove the wrapping tr tag
      this.$el.html( html );
      return this;
    },
    
    removeRule : function() {
      var self = this;
      this.$el.addClass("highlight").delay(2000).animate({opacity: 0}, 500, function() {
        self.remove();
      });
    },
    
    handleRemoveClick : function() {
      this.displayModal('views/RemoveRuleView', function() {
        this.model.destroy();
      });
    },
    
    displayModal : function(viewName, callback) {
      var self = this;
      require([viewName], function(View) {
        var view = new View({model : self.model});
        var modal = new Backbone.BootstrapModal({
          content : view,
          title : "Confirm Removal",
          animate : true,
          okText : view.getSubmitButtonText(),
          cancelText : view.getCancelButtonText()
        }).open( $.proxy(callback, self) );
      });
    }
  
  });
  
  return RuleRowView;
});