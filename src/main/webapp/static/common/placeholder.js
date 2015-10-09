(function($) {
  var placeholderfriend = {
    focus: function(s) {
      s = $(s).hide().prev().show().focus();
      var idValue = s.attr("id");
      if (idValue) {
        s.attr("id", idValue.replace("placeholderfriend", ""));
      }
      var clsValue = s.attr("class");
	  if (clsValue) {
        s.attr("class", clsValue.replace("placeholderfriend", ""));
      }
    }
  };


  function isPlaceholer() {
    var input = document.createElement('input');
    return "placeholder" in input;
  }

  if (!isPlaceholer()) {
    $(function() {

      var form = $(this);


      var elements = form.find("input[type='text'][placeholder]");
      elements.each(function() {
        var s = $(this);
        var pValue = s.attr("placeholder");
		var sValue = s.val();
        if (pValue) {
          if (sValue == '') {
            s.val(pValue);
          }
        }
      });

      elements.focus(function() {
        var s = $(this);
        var pValue = s.attr("placeholder");
		var sValue = s.val();
        if (sValue && pValue) {
          if (sValue == pValue) {
            s.val('');
          }
        }
      });

      elements.blur(function() {
        var s = $(this);
        var pValue = s.attr("placeholder");
		var sValue = s.val();
        if (!sValue) {
          s.val(pValue);
        }
      });


      var elementsPass = form.find("input[type='password'][placeholder]");
      elementsPass.each(function(i) {
        var s = $(this);
        var pValue = s.attr("placeholder");
		var sValue = s.val();
        if (pValue) {
          if (sValue == '') {
            var html = this.outerHTML || "";
            html = html.replace(/\s*type=(['"])?password\1/gi, " type=text placeholderfriend")
              .replace(/\s*placeholderfriend/, " placeholderfriend value='" + pValue
              + "' " + "onfocus='placeholderfriendfocus(this);' ");
            var idValue = s.attr("id");
            if (idValue) {
              s.attr("id", idValue + "placeholderfriend");
            }
            var clsValue = s.attr("class");
			     if (clsValue) {
              s.attr("class", clsValue + "placeholderfriend");
            }
			   //  alert(html);
            s.hide();
            s.after(html);
          }
        }
      });

      elementsPass.blur(function() {
        var s = $(this);
        var sValue = s.val();
        if (sValue == '') {
          var idValue = s.attr("id");
          if (idValue) {
            s.attr("id", idValue + "placeholderfriend");
          }
          var clsValue = s.attr("class");
		  if (clsValue) {
            s.attr("class", clsValue + "placeholderfriend");
          }
          s.hide().next().show();
        }
      });

    });
  }
  window.placeholderfriendfocus = placeholderfriend.focus;
})(jQuery);