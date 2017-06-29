Ext.onReady(function () {
	var store = new Ext.data.Store({
		sortInfo: {field: "date", direction: "DESC"},
		url: '/wishlist/all',

		reader: new Ext.data.JsonReader({
			root: ''
		}, [
			'id',
			'description',
			'date',
			'is_done'
		])
	});

	store.load();

	var wish_model = Ext.data.Record.create([
		'id',
		'description',
		'date',
		'is_done'
	]);

	var desc_edit = new Ext.form.TextField();
	var done_edit = new Ext.form.TextField();

	var grid = new Ext.grid.EditorGridPanel({
		id: 'button-grid',
		store: store,

		cm: new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
			{id: 'description', header: "Wish", dataIndex: 'description', sortable: true, editor: desc_edit},
			{id: 'date', header: "Date", dataIndex: 'date', sortable: true, width: 30},
			{id: 'is_done', header: "Is done?", dataIndex: 'is_done', sortable: true, width: 30, editor: done_edit}
		]),

		selModel: new Ext.grid.RowSelectionModel({
			singleSelect: true
		}),

		listeners: {
			afteredit: function (e) {
				var _id = e.record.data.id;
				var _desc = e.record.data.description;
				var _date = e.record.data.date;
				var _done = e.record.data.is_done;

				var conn = new Ext.data.Connection();

				conn.request({
					url: '/wishlist/update',
					method: 'PUT',
					headers: {'Content-Type': 'application/json'},
					jsonData: {
						"id": _id,
						"description": _desc,
						"date": _date,
						"is_done": _done
					},

					success: function (a, response) {
						grid.getStore().reload();
					},

					failure: function (a, response) {
						Ext.Msg.alert("Error", 'Wish has not changed!');
						grid.getStore().reload();
					}
				});
			}
		},

		viewConfig: {
			forceFit: true
		},

		tbar: [{
			text: 'Add wish',

			handler: function () {
				var form = new Ext.form.FormPanel({
					baseCls: 'x-plain',
					labelWidth: 75,
					name: 'MyForm',
					url: '/wishlist/add',
					defaultType: 'textfield',

					items: [{
						fieldLabel: 'Wish',
						id: 'description',
						name: 'description',
						xtype: 'textfield',
						maxLength: 64,
						allowBlank: false,
						width: 200,
						listeners: {
							afterrender: function (field) {
								field.focus(false, 200);
							}
						}
					}]
				});

				var window = new Ext.Window({
					title: 'Add wish',
					width: 300,
					height: 100,
					minWidth: 300,
					minHeight: 100,
					layout: 'fit',
					plain: true,
					buttonAlign: 'center',
					resizable: false,
					items: form,

					buttons: [{
						text: 'Add',
						handler: function () {
							if (form.getForm().isValid()) {
								Ext.Ajax.request({
									method: 'POST',
									url: '/wishlist/add',
									headers: {'Content-Type': 'application/json'},
									jsonData: {
										"id": "0",
										"description": Ext.getCmp('description').getValue(),
										"date": new Date(),
										"is_done": "false"
									},

									success: function (a, response) {
										grid.getStore().reload();

										window.close();
									},

									failure: function (a, response) {
										Ext.Msg.alert("Error", 'Wish has not added!');
									}
								});
							}
						}
					}, {
						text: 'Cancel',
						handler: function () {
							if (window) {
								window.close();
							}
						}
					}]
				});

				window.show();
			}
		}, '-', {
			text: 'Delete wish',
			tooltip: 'Delete selected wish',

			handler: function () {
				var sm = grid.getSelectionModel();
				var sel = sm.getSelected();

				if (sm.hasSelection()) {
					Ext.Msg.show({
						title: 'Deleting',
						buttons: Ext.MessageBox.YESNO,
						msg: 'Delete this wish?',

						fn: function (btn) {
							if (btn == 'yes') {
								var conn = new Ext.data.Connection();
								var restURL = '/wishlist/delete/' + sel.data.id;

								conn.request({
									method: 'DELETE',
									url: restURL,

									success: function (resp, opt) {
										grid.getStore().remove(sel);
									},

									failure: function (resp, opt) {
										Ext.Msg.alert('Error', 'Wish has not been deleted!');
									}
								});
							}
						}
					});
				}
			}
		}],

		width: 800,
		height: 300,
		collapsible: false,
		frame: false,
		clicksToEdit: 2,
		animCollapse: false,
		title: 'PetShop Wishlist',
		renderTo: document.body
	});
});
