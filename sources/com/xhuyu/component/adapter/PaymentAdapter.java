package com.xhuyu.component.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xhuyu.component.mvp.model.PayWayBean;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.utils.StringDesignUtil;
import com.xsdk.doraemon.utils.CheckUtil;
import java.util.List;

public class PaymentAdapter extends BaseExpandableListAdapter {
    private List<PayWayBean> mDataList;

    public PaymentAdapter(List<PayWayBean> dataList) {
        this.mDataList = dataList;
    }

    public int getGroupCount() {
        if (this.mDataList == null) {
            return 0;
        }
        return this.mDataList.size();
    }

    public int getChildrenCount(int groupPosition) {
        if (this.mDataList != null) {
            PayWayBean payWayBean = this.mDataList.get(groupPosition);
            if (payWayBean.isExpanded() && payWayBean.getChild() != null) {
                return payWayBean.getChild().size();
            }
        }
        return 0;
    }

    public PayWayBean getGroup(int groupPosition) {
        if (this.mDataList == null) {
            return null;
        }
        return this.mDataList.get(groupPosition);
    }

    public PayWayBean getChild(int groupPosition, int childPosition) {
        if (this.mDataList == null) {
            return null;
        }
        if (this.mDataList.get(groupPosition).getChild() == null) {
            return null;
        }
        return this.mDataList.get(groupPosition).getChild().get(childPosition);
    }

    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long) childPosition;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        PayWayBean payWayBean = this.mDataList.get(groupPosition);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceUtil.getLayoutId("adapter_payment_item"), (ViewGroup) null);
            convertView.setBackgroundColor(-1);
            viewHolder.tvPayment = (TextView) ResourceUtil.getWidgetView(convertView, "tv_payment");
            viewHolder.imgArrow = (ImageView) ResourceUtil.getWidgetView(convertView, "img_arrow");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (payWayBean.isExpanded()) {
            if (isExpanded) {
                viewHolder.imgArrow.setImageResource(ResourceUtil.getDrawableId("huyu_above_arrow"));
            } else {
                viewHolder.imgArrow.setImageResource(ResourceUtil.getDrawableId("huyu_arrow_bottom_down"));
            }
            viewHolder.imgArrow.setVisibility(0);
        } else {
            viewHolder.imgArrow.setVisibility(8);
        }
        String info = "";
        if (!CheckUtil.isEmpty(payWayBean.getName())) {
            info = payWayBean.getName();
        }
        if (!CheckUtil.isEmpty(info)) {
            PayWayBean childPayWay = getFirstChildPayWay(groupPosition);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(info);
            if (!CheckUtil.isEmpty(childPayWay.getWallet_amount())) {
                stringBuilder.append("（");
                stringBuilder.append(childPayWay.getWallet_amount());
                stringBuilder.append("）");
            }
            if (!CheckUtil.isEmpty(childPayWay.getTips())) {
                if (!CheckUtil.isEmpty(childPayWay.getTips())) {
                    stringBuilder.append("&nbsp;&nbsp;");
                    stringBuilder.append(childPayWay.getTips());
                }
                String keyword = stringBuilder.toString();
                if (!"&nbsp;&nbsp;".contains(keyword)) {
                    viewHolder.tvPayment.setText(StringDesignUtil.getSpanned(keyword, childPayWay.getTips(), "#FC425F", true));
                }
            }
            viewHolder.tvPayment.setText(stringBuilder.toString());
        }
        return convertView;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        PayWayBean payWayBean;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(ResourceUtil.getLayoutId("adapter_payment_item"), (ViewGroup) null);
            convertView.setBackgroundColor(Color.parseColor("#F6F6F6"));
            convertView.setPadding(20, 0, 20, 0);
            viewHolder.tvPayment = (TextView) ResourceUtil.getWidgetView(convertView, "tv_payment");
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!(this.mDataList.get(groupPosition) == null || this.mDataList.get(groupPosition).getChild() == null || (payWayBean = this.mDataList.get(groupPosition).getChild().get(childPosition)) == null)) {
            String info = "";
            if (!CheckUtil.isEmpty(payWayBean.getName())) {
                info = payWayBean.getName();
            }
            if (!CheckUtil.isEmpty(info)) {
                if (!CheckUtil.isEmpty(payWayBean.getTips())) {
                    String keyword = info + "&nbsp;&nbsp;" + payWayBean.getTips();
                    viewHolder.tvPayment.setText(StringDesignUtil.getSpanned(keyword, keyword.replace(info, ""), "#FC425F", true));
                } else {
                    viewHolder.tvPayment.setText(info);
                }
            }
        }
        return convertView;
    }

    public PayWayBean getFirstChildPayWay(int groupPosition) {
        List<PayWayBean> child;
        PayWayBean wayBean;
        PayWayBean payWayBean = this.mDataList.get(groupPosition);
        if (payWayBean == null || (child = payWayBean.getChild()) == null || child.size() <= 0 || (wayBean = child.get(0)) == null) {
            return null;
        }
        return wayBean;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        ImageView imgArrow;
        TextView tvPayment;

        ViewHolder() {
        }
    }
}
