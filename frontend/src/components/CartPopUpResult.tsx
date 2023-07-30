export const CartPopUpResult: React.FC<any> = (isError: boolean) => {
  console.log('isError - ', isError);
  return (
    <div
      className="modal fade"
      id="exampleModal"
      tabIndex={-1}
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true">
      <div className="modal-dialog" role="document">
        <div className="modal-content">
          <div className="modal-body">
            {!isError && (
              <div className="column" id="main">
                <h2>ERROR</h2>
              </div>
            )}
            {isError && (
              <div className="column" id="main">
                <h2>OK</h2>
              </div>
            )}
          </div>
        </div>
      </div>
      {/* Close button */}
    </div>
  );
};
