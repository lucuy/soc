package com.soc.model.alert;
/**
 * tbl_alert_interactiontable
 * @author yinhaiping
 *
 */
public class AlertInteractionTable {
	
	private Long interactionTableId;	//关联表ID
	private String deviceCategoryId;	//设备类型表tbl_device_category(DEVICE_CATEGORY_ID)
	private String assetId;				//设备名称tbl_asset(ASSET_ID)
	private String definitionId;		//事件类型tbl_event_definition(ID)
	
	public Long getInteractionTableId() {
		return interactionTableId;
	}
	public void setInteractionTableId(Long interactionTableId) {
		this.interactionTableId = interactionTableId;
	}
	public String getDeviceCategoryId() {
		return deviceCategoryId;
	}
	public void setDeviceCategoryId(String deviceCategoryId) {
		this.deviceCategoryId = deviceCategoryId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
}