//改变左侧菜单选中样式
    function changeMenuStyle(menuItem, opts, selectId) {
        menuItem.find("div.tree-node-selected").removeClass("tree-node-selected");
        var node = menuItem.tree("find", selectId);
        if (node) {
            $(node.target).addClass("tree-node-selected");
            opts.selectedItemId = node.id;
            menuItem.trigger("mouseleave.sidemenu");
        }

        changeMenuSelect(menuItem);
    }
	
	function changeMenuSelect(menus, opts, selectId) {
        var menutrees = menus.find(".sidemenu-tree");
        menutrees.each(function () {
            var menuItem = $(this);
            changeMenuStyle(menuItem, opts, selectId);
        });

        var tooltips = menus.find(".tooltip-f");
        tooltips.each(function () {
            var menuItem = $(this);
            var tip = menuItem.tooltip("tip");
            if (tip) {
                tip.find(".sidemenu-tree").each(function () {
                    changeMenuStyle($(this), opts, selectId);
                });
                menuItem.tooltip("reposition");
            }
        });
    }