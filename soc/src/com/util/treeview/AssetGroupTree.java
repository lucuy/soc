/*
 * 文 件 名:  AssetGroupTree.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.treeview;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.systemsetting.NodeGroup;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.systemsetting.NodeGroupService;
import com.util.CheckBox;
import com.util.Radio;
import com.util.Span;
import com.util.StringUtil;

/**
 * 资产组树形实现 <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-8-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AssetGroupTree {
	private String ctx;

	private AssetGroupService assetGroupManager;

	private StringBuffer htmlBuffer;
	private NodeGroupService nodeGroupManager;

	private Span span = null;

	private Radio radio = null;

	private CheckBox checkBox = null;

	private int m_InDent;

	public NodeGroupService getNodeGroupManager() {
		return nodeGroupManager;
	}

	public void setNodeGroupManager(NodeGroupService nodeGroupManager) {
		this.nodeGroupManager = nodeGroupManager;
	}

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	public Span getSpan() {
		return span;
	}

	public void setSpan(Span span) {
		this.span = span;
	}

	public Radio getRadio() {
		return radio;
	}

	public void setRadio(Radio radio) {
		this.radio = radio;
	}

	public int getM_InDent() {
		return m_InDent;
	}

	public void setM_InDent(int m_InDent) {
		this.m_InDent = m_InDent;
	}

	public StringBuffer getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(StringBuffer htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	/**
	 * <默认构造函数>
	 */
	public AssetGroupTree(AssetGroupService assetGroupManager, String ctx) {
		this.assetGroupManager = assetGroupManager;
		this.ctx = ctx;
		htmlBuffer = new StringBuffer();
	}

	public AssetGroupTree(NodeGroupService nodeGroupManager, String ctx) {
		this.nodeGroupManager = nodeGroupManager;
		this.ctx = ctx;
		htmlBuffer = new StringBuffer();
	}

	public String displayTree(long groupId) throws IOException {
		// //System.out.println("进入显示树函数");

		Map<String, Object> map = new HashMap<String, Object>();
		if (groupId == 1) {
			map.put("assetGroupParentId", 0);
		} else {
			AssetGroup group = assetGroupManager.queryById(groupId);
			map.put("assetGroupParentId", group.getAssetGroupParentId());
		}

		// //System.out.println(map.get("assetGroupParentId"));

		List<AssetGroup> assetGroupList = assetGroupManager
				.queryByParentId(map);

		if (assetGroupList.isEmpty()) {
			AssetGroup group = assetGroupManager.queryById(groupId);

			assetGroupList.add(group);

		}

		for (int i = 0; i < assetGroupList.size(); i++) {
			AssetGroup assetGroup = assetGroupList.get(i);
			if (assetGroup.getAssetGroupId() == groupId) {

				boolean hasSon = false;
				map.put("assetGroupParentId", assetGroup.getAssetGroupId());

				if (!assetGroupManager.queryByParentId(map).isEmpty()) {
					hasSon = true;
				}

				htmlBuffer.append("<div class=\"root_tree_node\">");
				htmlBuffer.append("<span style='display:block'>");

				if (hasSon) {
					htmlBuffer
							.append("<img id ='img_"
									+ assetGroup.getAssetGroupId()
									+ "' src=\""
									+ ctx
									+ "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('"
									+ assetGroup.getAssetGroupId()
									+ "')\"/>&nbsp;");
				} else {
					htmlBuffer
							.append("<img id ='img_"
									+ assetGroup.getAssetGroupId()
									+ "' src=\""
									+ ctx
									+ "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;");
				}
				// 显示单选按钮
				if (radio != null) {
					htmlBuffer.append(radio.show(assetGroup, "", ""));
				}
				if (checkBox != null) {
					htmlBuffer.append(checkBox.show(assetGroup, "", ""));
				}
				htmlBuffer.append("<s:hidden id="
						+ assetGroup.getAssetGroupId() + "_id  value=\""
						+ assetGroup.getAssetGroupName() + "\"/>");
				/* htmlBuffer.append("</s:hidden>"); */
				htmlBuffer.append("<s:hidden id="
						+ assetGroup.getAssetGroupId() + "_idF  value=\""
						+ assetGroup.getAssetParentsFeature() + "\"/>");
				/* htmlBuffer.append("</s:hidden>"); */
				if (span != null) {
					htmlBuffer.append(span.show(assetGroup));
				}

				else {
					htmlBuffer.append(assetGroup.getAssetGroupId());
				}
				htmlBuffer.append("</span>");

				// 显示其子节点
				htmlBuffer.append("<div id=\"span_node_"
						+ assetGroup.getAssetGroupId() + "\">");

				drawSon(assetGroup, "", "p_1");

				htmlBuffer.append("</div>");
				htmlBuffer.append("</div>");
			}
		}
		return htmlBuffer.toString();

	}

	public String displayTree() throws IOException {
		// //System.out.println("进入显示树函数");

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("assetGroupParentId", 0);

		// //System.out.println(map.get("assetGroupParentId"));

		List<AssetGroup> assetGroupList = assetGroupManager
				.queryByParentId(map);

		for (int i = 0; i < assetGroupList.size(); i++) {
			AssetGroup assetGroup = assetGroupList.get(i);
			boolean hasSon = false;
			map.put("assetGroupParentId", assetGroup.getAssetGroupId());

			if (!assetGroupManager.queryByParentId(map).isEmpty()) {
				hasSon = true;
			}

			htmlBuffer.append("<div class=\"root_tree_node\">");
			htmlBuffer.append("<span style='display:block'>");

			if (hasSon) {
				htmlBuffer
						.append("<img id ='img_"
								+ assetGroup.getAssetGroupId()
								+ "' src=\""
								+ ctx
								+ "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('"
								+ assetGroup.getAssetGroupId() + "')\"/>&nbsp;");
			} else {
				htmlBuffer.append("<img id ='img_"
						+ assetGroup.getAssetGroupId() + "' src=\"" + ctx
						+ "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;");
			}
			// 显示单选按钮
			if (radio != null) {
				htmlBuffer.append(radio.show(assetGroup, "", ""));
			}
			if (checkBox != null) {
				htmlBuffer.append(checkBox.show(assetGroup, "", ""));
			}
			htmlBuffer
					.append("<s:hidden id=" + assetGroup.getAssetGroupId()
							+ "_id  value=\"" + assetGroup.getAssetGroupName()
							+ "\"/>");
			/* htmlBuffer.append("</s:hidden>"); */
			htmlBuffer.append("<s:hidden id=" + assetGroup.getAssetGroupId()
					+ "_idF  value=\"" + assetGroup.getAssetParentsFeature()
					+ "\"/>");
			/* htmlBuffer.append("</s:hidden>"); */
			if (span != null) {
				htmlBuffer.append(span.show(assetGroup));
			}

			else {
				htmlBuffer.append(assetGroup.getAssetGroupId());
			}
			htmlBuffer.append("</span>");

			// 显示其子节点
			htmlBuffer.append("<div id=\"span_node_"
					+ assetGroup.getAssetGroupId() + "\">");

			drawSon(assetGroup, "", "p_1");

			htmlBuffer.append("</div>");
			htmlBuffer.append("</div>");
		}
		return htmlBuffer.toString();

	}

	public String displayNodeTree() throws IOException {
		// //System.out.println("进入显示树函数");

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("nodeGroupParentId", 0);

		// //System.out.println(map.get("assetGroupParentId"));

		List<NodeGroup> nodeGroupList = nodeGroupManager.queryByParentId(map);

		for (int i = 0; i < nodeGroupList.size(); i++) {
			NodeGroup nodeGroup = nodeGroupList.get(i);
			boolean hasSon = false;
			map.put("nodeGroupParentId", nodeGroup.getNodeGroupId());

			if (!nodeGroupManager.queryByParentId(map).isEmpty()) {
				hasSon = true;
			}

			htmlBuffer.append("<div class=\"root_tree_node\">");
			htmlBuffer.append("<span style='display:block'>");

			if (hasSon) {
				htmlBuffer
						.append("<img id ='img_"
								+ nodeGroup.getNodeGroupId()
								+ "' src=\""
								+ ctx
								+ "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('"
								+ nodeGroup.getNodeGroupId() + "')\"/>&nbsp;");
			} else {
				htmlBuffer.append("<img id ='img_" + nodeGroup.getNodeGroupId()
						+ "' src=\"" + ctx
						+ "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;");
			}
			// 显示单选按钮
			if (radio != null) {
				htmlBuffer.append(radio.show(nodeGroup, "", ""));
			}
			if (checkBox != null) {
				htmlBuffer.append(checkBox.show(nodeGroup, "", ""));
			}
			htmlBuffer.append("<s:hidden id=" + nodeGroup.getNodeGroupId()
					+ "_id  value=\"" + nodeGroup.getNodeGroupName() + "\"/>");
			/* htmlBuffer.append("</s:hidden>"); */
			htmlBuffer.append("<s:hidden id=" + nodeGroup.getNodeGroupId()
					+ "_idF  value=\"" + nodeGroup.getNodeGroupParentId()
					+ "\"/>");
			/* htmlBuffer.append("</s:hidden>"); */
			if (span != null) {
				htmlBuffer.append(span.show(nodeGroup));
			}

			else {
				htmlBuffer.append(nodeGroup.getNodeGroupId());
			}
			htmlBuffer.append("</span>");

			// 显示其子节点
			htmlBuffer.append("<div id=\"span_node_"
					+ nodeGroup.getNodeGroupId() + "\">");

			drawSonNode(nodeGroup, "", "p_1");

			htmlBuffer.append("</div>");
			htmlBuffer.append("</div>");
		}
		return htmlBuffer.toString();

	}

	public void drawSonNode(NodeGroup nodeGroup, String sIndentStr,
			String sDivId) {
		int iSonCounts;
		int iNowSon;
		String sIndent;
		String strSonIndent;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodeGroupParentId", nodeGroup.getNodeGroupId());
		List<NodeGroup> nodeGroupList = nodeGroupManager.queryByParentId(map);
		iSonCounts = nodeGroupList.size();
		iNowSon = 1;
		for (int i = 0; i <= iSonCounts; i++) {
			if (iNowSon <= iSonCounts) {
				NodeGroup ngp = nodeGroupList.get(i);
				// 如果不是最后一个儿子，那么显示的前缀为├
				if (iNowSon != iSonCounts) {
					sIndent = sIndentStr
							+ "<img src=\""
							+ ctx
							+ "/images/tree/node.gif\" />"
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/line.gif\" />", m_InDent);
					strSonIndent = sIndentStr
							+ "<img src=\""
							+ ctx
							+ "/images/tree/vertline.gif\" />"
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/blank.gif\" />", m_InDent);
				} else
				// 最后一个儿子的前缀应该是└
				{
					sIndent = sIndentStr
							+ "<img src=\""
							+ ctx
							+ "/images/tree/lastnode.gif\" />"
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/line.gif\" />", m_InDent);
					strSonIndent = sIndentStr
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/blank.gif\" />",
									m_InDent + 1);
				}

				htmlBuffer.append(sIndent);

				Map<String, Object> mapSon = new HashMap<String, Object>();

				mapSon.put("nodeGroupParentId", ngp.getNodeGroupId());

				List<NodeGroup> sonList = nodeGroupManager
						.queryByParentId(mapSon);

				if (sonList.size() > 0)// 如果有儿子，那么应该使用+号
				{
					htmlBuffer
							.append("<img id ='img_"
									+ ngp.getNodeGroupId()
									+ "' src=\""
									+ ctx
									+ "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('"
									+ ngp.getNodeGroupId() + "')\"/>&nbsp;");
				} else// 否则使用空
				{
					htmlBuffer
							.append("<img id ='img_"
									+ ngp.getNodeGroupId()
									+ "' src=\""
									+ ctx
									+ "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;");
				}
				if (radio != null) {
					htmlBuffer.append(radio.show(ngp, "", ""));
				}
				if (checkBox != null) {
					htmlBuffer.append(checkBox.show(ngp, "", ""));
				}
				htmlBuffer.append("<s:hidden id=" + ngp.getNodeGroupId()
						+ "_id  value=\"" + ngp.getNodeGroupName() + "\"/>");
				/* htmlBuffer.append("</s:hidden>"); */
				htmlBuffer.append("<s:hidden id=" + ngp.getNodeGroupId()
						+ "_idF  value=\"" + ngp.getNodeGroupParentId()
						+ "\"/>");
				/* htmlBuffer.append("</s:hidden>"); */
				if (span != null) {
					htmlBuffer.append(span.show(ngp));
				} else {
					htmlBuffer.append(ngp.getNodeGroupName());
				}
				htmlBuffer.append("<br>");
				htmlBuffer.append("<div id=\"d" + sDivId + "_" + iNowSon
						+ "\" style=\"display:block\">");
				htmlBuffer.append("<div id=\"span_node_" + ngp.getNodeGroupId()
						+ "\">");

				drawSonNode(ngp, strSonIndent, sDivId + "_" + iNowSon);

				iNowSon = iNowSon + 1;
				htmlBuffer.append("</div>");
				htmlBuffer.append("</div>");
			}
		}
	}

	public void drawSon(AssetGroup ag, String sIndentStr, String sDivId) {
		int iSonCounts;
		int iNowSon;
		String sIndent;
		String strSonIndent;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetGroupParentId", ag.getAssetGroupId());
		List<AssetGroup> assetGroupList = assetGroupManager
				.queryByParentId(map);
		iSonCounts = assetGroupList.size();
		iNowSon = 1;
		for (int i = 0; i <= iSonCounts; i++) {
			if (iNowSon <= iSonCounts) {
				AssetGroup agp = assetGroupList.get(i);
				// 如果不是最后一个儿子，那么显示的前缀为├
				if (iNowSon != iSonCounts) {
					sIndent = sIndentStr
							+ "<img src=\""
							+ ctx
							+ "/images/tree/node.gif\" />"
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/line.gif\" />", m_InDent);
					strSonIndent = sIndentStr
							+ "<img src=\""
							+ ctx
							+ "/images/tree/vertline.gif\" />"
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/blank.gif\" />", m_InDent);
				} else
				// 最后一个儿子的前缀应该是└
				{
					sIndent = sIndentStr
							+ "<img src=\""
							+ ctx
							+ "/images/tree/lastnode.gif\" />"
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/line.gif\" />", m_InDent);
					strSonIndent = sIndentStr
							+ dupchr("<img src=\"" + ctx
									+ "/images/tree/blank.gif\" />",
									m_InDent + 1);
				}

				htmlBuffer.append(sIndent);

				Map<String, Object> mapSon = new HashMap<String, Object>();

				mapSon.put("assetGroupParentId", agp.getAssetGroupId());

				List<AssetGroup> sonList = assetGroupManager
						.queryByParentId(mapSon);

				if (sonList.size() > 0)// 如果有儿子，那么应该使用+号
				{
					htmlBuffer
							.append("<img id ='img_"
									+ agp.getAssetGroupId()
									+ "' src=\""
									+ ctx
									+ "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('"
									+ agp.getAssetGroupId() + "')\"/>&nbsp;");
				} else// 否则使用空
				{
					htmlBuffer
							.append("<img id ='img_"
									+ agp.getAssetGroupId()
									+ "' src=\""
									+ ctx
									+ "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;");
				}
				if (radio != null) {
					htmlBuffer.append(radio.show(agp, "", ""));
				}
				if (checkBox != null) {
					htmlBuffer.append(checkBox.show(agp, "", ""));
				}
				htmlBuffer.append("<s:hidden id=" + agp.getAssetGroupId()
						+ "_id  value=\"" + agp.getAssetGroupName() + "\"/>");
				/* htmlBuffer.append("</s:hidden>"); */
				htmlBuffer.append("<s:hidden id=" + agp.getAssetGroupId()
						+ "_idF  value=\"" + agp.getAssetParentsFeature()
						+ "\"/>");
				/* htmlBuffer.append("</s:hidden>"); */
				if (span != null) {
					htmlBuffer.append(span.show(agp));
				} else {
					htmlBuffer.append(agp.getAssetGroupName());
				}
				htmlBuffer.append("<br>");
				htmlBuffer.append("<div id=\"d" + sDivId + "_" + iNowSon
						+ "\" style=\"display:block\">");
				htmlBuffer.append("<div id=\"span_node_"
						+ agp.getAssetGroupId() + "\">");

				drawSon(agp, strSonIndent, sDivId + "_" + iNowSon);

				iNowSon = iNowSon + 1;
				htmlBuffer.append("</div>");
				htmlBuffer.append("</div>");
			}
		}
	}

	/**
	 * 用在事件综合月报的自定义条件中 拼接住zTree需要的字字符串， 之前的方法太乱 现在重新写
	 * 
	 * @param groupId
	 * @return
	 * @throws IOException
	 */
	public void drawSonZTree(AssetGroup ag, String sIndentStr, String sDivId ,AssetService assetManager) {
		int iSonCounts;
		int iNowSon;
		String sIndent;
		String strSonIndent;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetGroupParentId", ag.getAssetGroupId());
		List<AssetGroup> assetGroupList = assetGroupManager
				.queryByParentId(map);
		iSonCounts = assetGroupList.size();
		iNowSon = 1;
		for (int i = 0; i <= iSonCounts; i++) {
			if (iNowSon <= iSonCounts) {
				AssetGroup agp = assetGroupList.get(i);
				// 如果不是最后一个儿子，那么显示的前缀为├
				// 无论有没有son都的根据资产组的id找一下他下面的资产，然后品一下串
				List<Asset> assetList = assetManager
						.queryAssetByAssetGroupId(agp.getAssetGroupId());
				htmlBuffer.append("{id:")
						.append(agp.getAssetGroupId()).append(",pId:")
						.append(agp.getAssetGroupParentId())
						.append(",name:\"").append(agp.getAssetGroupName());
						
				if (!assetList.isEmpty()) {
					htmlBuffer.append("\"},");

				} else {
					htmlBuffer.append("\"  , iconSkin:\"icon01\"},");
				}
				sIndent = htmlBuffer.toString();
				// htmlBuffer.append(sIndent);

				Map<String, Object> mapSon = new HashMap<String, Object>();

				mapSon.put("assetGroupParentId", agp.getAssetGroupId());

				List<AssetGroup> sonList = assetGroupManager
						.queryByParentId(mapSon);
					if (!assetList.isEmpty()) {// 不是空的话就拼字符串 这里的id用assetId 代替
											// 怕的是id一样的话树形结构用出现数据 错误 提交的话 遍历所有节点
											// 找到图标是默认的而且是多选框选中的，拼接id的字符串 传到后台
					for (Asset asset : assetList) {
						htmlBuffer.append("{assetId:")
						.append(asset.getAssetId())
						.append(",pId:")
						.append(agp.getAssetGroupId())
						.append(",name:\"")
						.append(asset.getAssetName())
						.append("\", iconSkin:\"icon02\"},");
					}
				}
				if (sonList.size() > 0)// 如果有儿子，那么应该使用+号
				{
					drawSonZTree(agp, "", sDivId + "_" + iNowSon,assetManager);
				}

				iNowSon = iNowSon + 1;

			}
		}
	}

	public String dupchr(String str, int num) {
		String dupchr = "";

		int iTmp = 1;

		String strtmp = "";

		if (StringUtil.isBlank(str)) {
			return dupchr;
		} else {
			for (int i = 0; i <= num; i++) {
				strtmp = strtmp + str;
				iTmp = iTmp + 1;
			}
			dupchr = strtmp;
		}
		return dupchr;
	}

	/**
	 * 显示子节点 <功能详细描述>
	 * 
	 * @param parentId
	 *            Integer
	 * @throws IOException
	 *             IOException
	 * @see [类、类#方法、类#成员]
	 */
	public void drawSon(Long parentId) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetGroupParentId", parentId);

		List<AssetGroup> assetGroupList = assetGroupManager
				.queryByParentId(map);

		htmlBuffer.append("<div>");
		for (int i = 0; i < assetGroupList.size(); i++) {
			AssetGroup obj = assetGroupList.get(i);

			String indexs = String.valueOf(obj.getAssetGroupId());

			boolean hasSon = false;

			map.put("assetGroupParentId", obj.getAssetGroupId());
			if (!assetGroupManager.queryByParentId(map).isEmpty()) {
				hasSon = true;
			}

			// 显示节点图标与描述
			htmlBuffer.append("<div>");

			if (i == assetGroupList.size() - 1) {
				htmlBuffer.append("<img src=\"" + ctx
						+ "/images/tree/vertline.gif\" />");
				htmlBuffer.append("<img src=\"" + ctx
						+ "/images/tree/blank.gif\" />");
				htmlBuffer.append("<img src=\"" + ctx
						+ "/images/tree/lastnode.gif\" />");
				htmlBuffer.append("<img src=\"" + ctx
						+ "/images/tree/line.gif\" />");
			} else {
				htmlBuffer.append("<img src=\"" + ctx
						+ "/images/tree/node.gif\" />");
				htmlBuffer.append("<img src=\"" + ctx
						+ "/images/tree/blank.gif\" />");

			}
			if (hasSon) {
				htmlBuffer
						.append("<img id ='img_"
								+ indexs
								+ "' src=\""
								+ ctx
								+ "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('"
								+ indexs + "')\"/>&nbsp;");
			} else {
				htmlBuffer.append("<img id ='img_" + indexs + "' src=\"" + ctx
						+ "/images/tree/minusno.gif\" class=\"hand\"/>&nbsp;");
			}
			if (radio != null) {
				htmlBuffer.append(radio.show(obj, "", ""));
			}
			if (checkBox != null) {
				htmlBuffer.append(checkBox.show(obj, "", ""));
			}
			if (span != null) {
				htmlBuffer.append(span.show(obj));
			} else {

				htmlBuffer.append(obj.getAssetGroupName());
			}
			htmlBuffer.append("</div>");

			// 递归调用，递归显示其子节点
			htmlBuffer.append("<div id=\"span_node_" + obj.getAssetGroupId()
					+ "\" >");

			drawSon(obj.getAssetGroupId());

			htmlBuffer.append("</div>");

		}
		htmlBuffer.append("</div>");

	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

	/**
	 * 用在事件综合月报的自定义条件中 拼接住zTree需要的字字符串， 之前的方法太乱 现在重新写
	 * 
	 * @param groupId
	 * @return
	 * @throws IOException
	 */
	public String displayZTree(long groupId, AssetService assetManager)
			throws IOException {
		// //System.out.println("进入显示树函数");

		Map<String, Object> map = new HashMap<String, Object>();
		if (groupId == 1) {
			map.put("assetGroupParentId", 0);
		} else {
			AssetGroup group = assetGroupManager.queryById(groupId);
			map.put("assetGroupParentId", group.getAssetGroupParentId());
		}
		List<AssetGroup> assetGroupList = assetGroupManager
				.queryByParentId(map);
		if (assetGroupList.isEmpty()) {
			AssetGroup group = assetGroupManager.queryById(groupId);
			assetGroupList.add(group);
		}
		for (int i = 0; i < assetGroupList.size(); i++) {
			AssetGroup assetGroup = assetGroupList.get(i);
			if (assetGroup.getAssetGroupId() == groupId) {
				boolean hasSon = false;
				// 无论有没有son都的根据资产组的id找一下他下面的资产，然后品一下串
				List<Asset> assetList = assetManager
						.queryAssetByAssetGroupId(assetGroup.getAssetGroupId());
				map.put("assetGroupParentId", assetGroup.getAssetGroupId());
				if (!assetGroupManager.queryByParentId(map).isEmpty()) {
					hasSon = true;
				}
				htmlBuffer.append("{id:")
				.append(assetGroup.getAssetGroupId())
				.append(",pId:")
				.append(assetGroup.getAssetGroupParentId())
				.append(",name:\"")
				.append(assetGroup.getAssetGroupName());
				
				if (!assetList.isEmpty()) {
					htmlBuffer.append("\"  },");
				} else {
					htmlBuffer.append("\" , iconSkin:\"icon01\" },");
				}
			
				if (!assetList.isEmpty()) {// 不是空的话就拼字符串 这里的id用assetId 代替
											// 怕的是id一样的话树形结构用出现数据 错误 提交的话 遍历所有节点
											// 找到图标是默认的而且是多选框选中的，拼接id的字符串 传到后台
					for (Asset asset : assetList) {
						htmlBuffer.append("{assetId:")
						.append(asset.getAssetId())
						.append(",pId:")
						.append(assetGroup.getAssetGroupId())
						.append(",name:\"")
						.append(asset.getAssetName())
						.append("\", iconSkin:\"icon02\"},");
					}
				}
				drawSonZTree(assetGroup, "", "p_1",assetManager);

			}
		}
		return htmlBuffer.toString();

	}
}
