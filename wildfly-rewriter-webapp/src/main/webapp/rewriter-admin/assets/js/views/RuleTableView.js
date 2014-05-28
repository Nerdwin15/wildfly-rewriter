
define([
  'backbone',
  'text!templates/tableTemplate.xhtml',
  'views/RuleRowView'
  ], function(Backbone, template, RowView) {
  
  var RuleTableView = Backbone.View.extend({
    $tbody : null,
    
    initialize : function() {
      this.listenTo(this.collection, "add", this.addRule);
    },
    
    render: function() {
      this.$el.html( template );
      this.$tbody = this.$("tbody");
      return this;
    },
    
    addRule : function(model) {
      var row = new RowView({ model : model });
      this.$tbody.append(row.render().$el);
    }
  
  });
  
  return RuleTableView;
});