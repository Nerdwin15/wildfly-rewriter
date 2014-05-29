
define([
  'jquery',
  'collections/RuleCollection',
  'views/RuleTableView',
  'models/RuleModel'
  ], function($, RuleCollection, RuleTableView, RuleModel) {
  
  var tableView = new RuleTableView({ 
    collection: RuleCollection, 
    el : $("#ruleTableWrapper") 
  });
  tableView.render();
  
  $("#addRuleButton").click(function() {
    require(['views/AddRuleView'], function(AddRuleView) {
      var view = new AddRuleView({model : new RuleModel() });
      new Backbone.BootstrapModal({
        content : view,
        title : "Add Rewrite Rule",
        animate : true,
        okText : view.getSubmitButtonText(),
        cancelText : view.getCancelButtonText()
      }).open();
    });
  });
  
  $("#testRewriteTrigger").click(function() {
    require(['views/TestRuleView'], function(TestRuleView) {
      var view = new TestRuleView();
      new Backbone.BootstrapModal({
        content : view,
        title : "Rewrite Tester",
        animate : true,
        okText : view.getSubmitButtonText(),
        cancelText : view.getCancelButtonText()
      }).open();
    });
  });
  
  $("#reloadRulesTrigger").click(function() {
    var $this = $(this);
    $this.button('loading');
    setTimeout(function() {
      $.post(CONTEXT_ROOT + "/api/routing/refresh", function() {
        $this.button('reset');
      });
    }, 500);
  });
  
  
  RuleCollection.fetch();
//  RuleCollection.add(new RuleModel({from : "/ws/something/", to: "/app/somethingElse/", id : 123}));
//  RuleCollection.add(new RuleModel({from : "/ws/am/", to: "/app/dfji/", id : 321}));
  
});
