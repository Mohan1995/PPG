package com.nauroo.ppg.ui.home.interactiveguide.certificaciones;

import android.app.Activity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nauroo.ppg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohan M on 6/5/2018.
 */

public class CertificationsAdapter extends RecyclerView.Adapter<CertificationsAdapter.ViewHolder> {
    Activity context;
    List<String> certificationsList;
    int firstTime = 0;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RecyclerView recyclerView;
        LinearLayout topLayout,bottomLayout;
        ImageView arrowImageView;
        public ViewHolder(View v) {
            super(v);
            textView=(TextView)v.findViewById(R.id.textView);
            recyclerView=(RecyclerView)v.findViewById(R.id.recyclerView);
            topLayout=(LinearLayout)v.findViewById(R.id.topLayout);
            bottomLayout=(LinearLayout)v.findViewById(R.id.bottomLayout);
            arrowImageView=(ImageView)v.findViewById(R.id.arrowImageView);
        }
    }


    public CertificationsAdapter(Activity context, List<String> certificationsList) {
        this.context = context;
        this.certificationsList = certificationsList;
    }


    @Override
    public CertificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.certifications_layout, parent, false);

        CertificationsAdapter.ViewHolder vh = new CertificationsAdapter.ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final CertificationsAdapter.ViewHolder holder, final int position) {
        if (firstTime == 0) {
            holder.topLayout.setTag("0");
            if (position == certificationsList.size() - 1) {
                firstTime = 1;
            }
        }
        holder.textView.setText(certificationsList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.topLayout.getTag().toString().equals("0")){
                    holder.topLayout.setTag("1");
                    holder.bottomLayout.setVisibility(View.VISIBLE);
                    holder.arrowImageView.setRotation(90.0f);
                    if (position==0) {
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("AMERCOAT 240");
                        principlesArrayList.add("SIGMAGUARD CSF 650");
                        principlesArrayList.add("NOVAGUARD 810");
                        principlesArrayList.add("NOVAGUARD 840");
                        principlesArrayList.add("NOVAGUARD 890");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==1){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("STEELGUARD 651");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("BRE Global Limited");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }
                    else if (position==2){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("Portafolio Especificación CFE");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("LAPEM");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }
                    else if (position==3){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("SIGMACOVER 350");
                        principlesArrayList.add("SIGMACOVER 350");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("PRA Coatings Technology Centre (UK)");
                        certifierArrayList.add("Scientific & Technical Services (STS)");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==4){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("PITT-CHAR XP");
                        principlesArrayList.add("PITT-CHAR XP");
                        principlesArrayList.add("PITT-CHAR XP");
                        principlesArrayList.add("PITT-CHAR XP2");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("Bureau Veritas");
                        certifierArrayList.add("Lloyd's Register");
                        certifierArrayList.add("Underwriters Laboratory");
                        certifierArrayList.add("Underwriters Laboratory");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==5){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("PHENGUARD 940");
                        principlesArrayList.add("PHENGUARD 940");
                        principlesArrayList.add("PHENGUARD 940");



                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("American Bureau of Shipping");
                        certifierArrayList.add("Det Norske Veritas");
                        certifierArrayList.add("Lloyd's Register");


                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==6){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("AMERCOAT 240");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");


                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("American Bureau of Shipping");
                        certifierArrayList.add("Bureau Veritas");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        certifierArrayList.add("Lloyd's Register");
                        certifierArrayList.add("American Bureau of Shipping");
                        certifierArrayList.add("Korean Register of Shipping");
                        certifierArrayList.add("Nippon Kaiji Kyokai");
                        certifierArrayList.add("Registro Italiano Navale");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }

                    else if (position==7){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("AMERCOAT 240");
                        principlesArrayList.add("AMERCOAT 385");
                        principlesArrayList.add("AMERCOAT 450 H");
                        principlesArrayList.add("AMERCOAT 450 H");
                        principlesArrayList.add("AMERCOAT 68 HS");
                        principlesArrayList.add("AMERCOAT 68 HS");
                        principlesArrayList.add("AMERLOCK 2");
                        principlesArrayList.add("AMERSHIELD");
                        principlesArrayList.add("PSX 700");
                        principlesArrayList.add("PSX 700");
                        principlesArrayList.add("PSX 700");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 350");
                        principlesArrayList.add("SIGMACOVER 410 MIO");
                        principlesArrayList.add("SIGMADUR 550");
                        principlesArrayList.add("SIGMAFAST 278");
                        principlesArrayList.add("SIGMAFAST 278");
                        principlesArrayList.add("SIGMAFAST 278");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("IFO");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("KTA-Tator, Inc.");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("KTA-Tator, Inc.");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("IFO");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("INSTITUTE for ENGINEERING of POLYMER MATERIALS and DYES");
                        certifierArrayList.add("KTA-Tator, Inc.");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("EXOVA UK Manchester");
                        certifierArrayList.add("COT bv Haarlem");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==8){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("PHENGUARD 940");
                        principlesArrayList.add("PSX 700");
                        principlesArrayList.add("SIGMADUR 550");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("EXOVA UK Manchester");
                        certifierArrayList.add("COT bv Haarlem");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==9){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("AMERCOAT 385");
                        principlesArrayList.add("PITT-CHAR XP");
                        principlesArrayList.add("SIGMA VIKOTE 56");
                        principlesArrayList.add("SIGMA VIKOTE 56");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMADUR 550");
                        principlesArrayList.add("SIGMADUR 550");
                        principlesArrayList.add("SIGMADUR 550");
                        principlesArrayList.add("SIGMARINE 24");
                        principlesArrayList.add("SIGMARINE 28");
                        principlesArrayList.add("SIGMARINE 28");
                        principlesArrayList.add("SIGMARINE 28");
                        principlesArrayList.add("SIGMARINE 48");
                        principlesArrayList.add("SIGMARINE 48");
                        principlesArrayList.add("SIGMARINE 48");
                        principlesArrayList.add("SIGMARINE 48");


                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("US COAST GUARD");
                        certifierArrayList.add("Korean Register of Shipping");
                        certifierArrayList.add("Det Norske Veritas");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        certifierArrayList.add("Det Norske Veritas");
                        certifierArrayList.add("Russian Maritime Register of Shipping (RMRS)");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        certifierArrayList.add("Det Norske Veritas");
                        certifierArrayList.add("Russian Maritime Register of Shipping (RMRS)");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        certifierArrayList.add("Marine Surveyors (UK DOT)");
                        certifierArrayList.add("Det Norske Veritas");
                        certifierArrayList.add("Russian Maritime Register of Shipping (RMRS)");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        certifierArrayList.add("Det Norske Veritas");
                        certifierArrayList.add("Russian Maritime Register of Shipping (RMRS)");
                        certifierArrayList.add("Korean Register of Shipping");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==10){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("AMERCOAT 385");
                        principlesArrayList.add("AMERCOAT 450 H");
                        principlesArrayList.add("AMERLOCK 2");
                        principlesArrayList.add("DIMETCOTE 9");
                        principlesArrayList.add("PHENGUARD 940");
                        principlesArrayList.add("PSX 700");
                        principlesArrayList.add("SIGMACOVER 380");
                        principlesArrayList.add("SIGMADUR 550");
                        principlesArrayList.add("SIGMADUR 550");
                        principlesArrayList.add("SIGMAFAST 278");
                        principlesArrayList.add("SIGMAFAST 278");


                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("CHARTER COATINGS");
                        certifierArrayList.add("EXOVA UK Manchester");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("EXOVA UK Manchester");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("EXOVA UK Manchester");
                        certifierArrayList.add("COT bv Haarlem");
                        certifierArrayList.add("EXOVA UK Manchester");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==11){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("Portafolio Especificación PEMEX");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("IMP");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }
                    else if (position==12){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("Portafolio Especificación PEMEX");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("CIDETEQ");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==13){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("SIGMAGUARD CSF 585");
                        principlesArrayList.add("SIGMAGUARD CSF 585");
                        principlesArrayList.add("SIGMAGUARD CSF 585");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("KIWA");
                        certifierArrayList.add("NSF International");
                        certifierArrayList.add("Water Regulations Advisory Scheme (WRAS)");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==14){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("ABC 3");
                        principlesArrayList.add("ABC 3");
                        principlesArrayList.add("ABC 3");
                        principlesArrayList.add("ABC 4");
                        principlesArrayList.add("ABC 4");
                        principlesArrayList.add("ABC 4");
                        principlesArrayList.add("SIGMA ECOFLEET 530");
                        principlesArrayList.add("SIGMA ECOFLEET 530");
                        principlesArrayList.add("SIGMA ECOFLEET 530");


                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("PPG");
                        certifierArrayList.add("Lloyd's Register");
                        certifierArrayList.add("American Bureau of Shipping");
                        certifierArrayList.add("Lloyd's Register");
                        certifierArrayList.add("PPG");
                        certifierArrayList.add("American Bureau of Shipping");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");
                        certifierArrayList.add("PPG");
                        certifierArrayList.add("Lloyd's Register");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==15){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("PHENGUARD 940");
                        principlesArrayList.add("SIGMAGUARD CSF 650 ");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("Lloyd's Register");
                        certifierArrayList.add("Det Norske Veritas - Germanischer Lloyd");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }else if (position==16){
                        ArrayList<String> principlesArrayList = new ArrayList<String>();
                        principlesArrayList.add("AMERCOAT 68 HS");
                        principlesArrayList.add("AMERCOAT 68 HS");
                        principlesArrayList.add("DIMETCOTE 9");
                        principlesArrayList.add("SIGMAZINC 158");

                        ArrayList<String> certifierArrayList = new ArrayList<String>();
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("KTA-Tator, Inc.");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");
                        certifierArrayList.add("Corrosion Control Consultants and Labs");

                        setUpRecyclerView(holder.recyclerView, principlesArrayList, certifierArrayList);
                    }
                }else {
                    holder.topLayout.setTag("0");
                    holder.bottomLayout.setVisibility(View.GONE);
                    holder.arrowImageView.setRotation(00.0f);
                }
            }
        });
    }

    private void setUpRecyclerView(RecyclerView recyclerView, ArrayList<String> principlesArrayList, ArrayList<String> certifierArrayList) {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        PrincipleAndCertifierAdapter principleAndCertifierAdapter=new PrincipleAndCertifierAdapter(context,principlesArrayList,certifierArrayList);
        recyclerView.setAdapter(principleAndCertifierAdapter);
    }


    @Override
    public int getItemCount() {
        return certificationsList.size();
    }
}