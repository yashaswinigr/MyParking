package com.example.user.myparkingapp.User.Adapter;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import com.example.user.myparkingapp.R;
        import com.example.user.myparkingapp.User.UserBookingActivity;
        import com.example.user.myparkingapp.User.Models.ParkingListForUserModel;
        import com.example.user.myparkingapp.User.UserDetailBookingActivity;

        import java.util.List;


public class ParkingListForUserAdapter extends RecyclerView.Adapter<ParkingListForUserAdapter.ViewHolder> {


    Context context;
    List<ParkingListForUserModel> list ;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView address,mobile_no,name,property_id;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardViewClick);
            property_id=(TextView)itemView.findViewById(R.id.property_id);
            address=(TextView)itemView.findViewById(R.id.address);
            mobile_no=(TextView)itemView.findViewById(R.id.mobileno);
            name=(TextView)itemView.findViewById(R.id.name);
        }
    }
    public ParkingListForUserAdapter(Context context, List<ParkingListForUserModel> parkingList) {
        this.context=context;
        this.list=parkingList;

    }

    @Override
    public ParkingListForUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parking_list_content,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ParkingListForUserAdapter.ViewHolder holder, int position) {
        final ParkingListForUserModel list1 = list.get(position);

        holder.address.setText(list1.getAddress());
        holder.mobile_no.setText(list1.getMobileNo());
        holder.name.setText(list1.getName());
        holder.property_id.setText(list1.getPropertyId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, UserDetailBookingActivity.class);
                    intent.putExtra("name",list1.getName());
                    intent.putExtra("mobile_no",list1.getMobileNo());
                    intent.putExtra("address",list1.getAddress());
                    intent.putExtra("property_id",list1.getPropertyId());
                context.startActivity(intent);
            }
        });
       // holder.pincode.setText(list1.getMobileNo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
