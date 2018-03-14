//
//  ChongQing Funds Charge Server Slice
//
//
//
#ifndef __FUNDS_ICE__
#define __FUNDS_ICE__
// module name for the company
module com {
	
	module palmcommerce {
		
		// module name for the module.
		module funds{ 
		
			module service {
				
				
				exception ProtocolProcessException {
					string state;
					string reason;
				};
				exception ProtocolStorageException {
					string state;
					string reason;
				};
				
				
				
				
				
				interface ProtocolStorage{
					
					bool save(string global,string transcode,string fromcode,string tocode,string request,string response) throws ProtocolStorageException;
					
				};
				
				interface ProtocolProcessor{
					bool doProcess(string transcode,string fromcode,string tocode,string request,out string response) throws ProtocolProcessException;
				};
				
				 
				
			};
		
		};
	
		
	};

};

#endif
